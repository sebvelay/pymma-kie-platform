/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.chtijbug.drools.proxy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.CamelContext;
import org.chtijbug.drools.proxy.camel.DroolsRouter;
import org.chtijbug.drools.proxy.persistence.ContainerRepository;
import org.chtijbug.drools.proxy.persistence.model.ContainerPojoPersist;
import org.chtijbug.kieserver.services.drools.DroolsChtijbugKieServerExtension;
import org.chtijbug.kieserver.services.drools.DroolsChtijbugRulesExecutionService;
import org.kie.server.api.model.*;
import org.kie.server.services.api.KieContainerInstance;
import org.kie.server.services.api.KieServerExtension;
import org.kie.server.services.api.KieServerRegistry;
import org.kie.server.services.impl.KieContainerInstanceImpl;
import org.kie.server.services.impl.KieServerImpl;
import org.kie.server.services.impl.KieServerLocator;
import org.kie.server.services.impl.marshal.MarshallerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service("kieService")
public class KieServiceCommon {


    private static final Logger logger = LoggerFactory.getLogger(KieServiceCommon.class);

    private KieServerImpl server;
    private MarshallerHelper marshallerHelper;
    private KieServerRegistry registry;
    private ObjectMapper mapper = new ObjectMapper();
    private DroolsChtijbugRulesExecutionService droolsChtijbugRulesExecutionService = null;

    private DroolsChtijbugKieServerExtension droolsChtijbugKieServerExtension;
    @Inject
    private ContainerRepository containerRepository;

    @Autowired
    CamelContext camelContext;

    public KieServiceCommon() {
        // for now, if no server impl is passed as parameter, create one
        this.server = KieServerLocator.getInstance();

        List<KieServerExtension> serverExtensions = this.server.getServerExtensions();
        for (KieServerExtension serverExtension : serverExtensions) {
            if (serverExtension instanceof DroolsChtijbugKieServerExtension) {
                droolsChtijbugKieServerExtension = (DroolsChtijbugKieServerExtension) serverExtension;
                if (droolsChtijbugRulesExecutionService == null) {

                    droolsChtijbugRulesExecutionService = droolsChtijbugKieServerExtension.getRulesExecutionService();
                }
                if (registry == null) {
                    registry = droolsChtijbugRulesExecutionService.getContext();
                }
            }
        }
        this.marshallerHelper = new MarshallerHelper(this.server.getServerRegistry());

    }

    @PostConstruct
    private void initCamelBusinessRoutes() {
        try {
            String serverName = System.getProperty("org.kie.server.id");
            List<ContainerPojoPersist> containers = containerRepository.findByServerName(serverName);
            for (ContainerPojoPersist container : containers) {
                ClassLoader localClassLoader = null;
                String containerId = container.getContainerId();
                KieContainerInstanceImpl kieContainerInstance = registry.getContainer(containerId);
                if (kieContainerInstance != null) {
                    try {
                        localClassLoader = Thread.currentThread()
                                .getContextClassLoader();
                    } catch (ClassCastException e) {
                        logger.info("GenericResource.runSession", e);
                    }
                    try {
                        Set<Class<?>> classes = kieContainerInstance.getExtraClasses();
                        String className = container.getClassName();
                        Class foundClass = this.getClassFromName(classes, className);
                        ClassLoader classLoader = foundClass.getClassLoader();
                        Class<?> theClass = classLoader.loadClass(className);
                        camelContext.setApplicationContextClassLoader(classLoader);
                        Thread.currentThread().setContextClassLoader(classLoader);
                        String projectName = container.getProjectName();
                        String processId = container.getProcessID();
                        DroolsRouter droolsRouter = new DroolsRouter(camelContext, theClass, projectName, kieContainerInstance, processId);
                        camelContext.addRoutes(droolsRouter);
                    } finally {
                        if (localClassLoader != null) {
                            Thread.currentThread().setContextClassLoader(localClassLoader);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.info("CreationContainer", e);
        }
    }

    public KieServerImpl getServer() {
        return server;
    }

    public void setServer(KieServerImpl server) {
        this.server = server;
    }

    public KieServerInfo getInfo() {
        ServiceResponse<KieServerInfo> result = server.getInfo();
        return result.getResult();
    }

    public KieContainerResourceList listContainers(String groupId, String artifactId, String version, String status) {
        ReleaseIdFilter releaseIdFilter = new ReleaseIdFilter.Builder()
                .groupId(groupId)
                .artifactId(artifactId)
                .version(version)
                .build();

        KieContainerStatusFilter statusFilter = KieContainerStatusFilter.parseFromNullableString(status);
        KieContainerResourceFilter containerFilter = new KieContainerResourceFilter(releaseIdFilter, statusFilter);
        ServiceResponse<KieContainerResourceList> result = server.listContainers(containerFilter);
        return result.getResult();
    }


    public KieContainerResource createContainerWithRestBusinessService(String id, KieContainerResource container, String className, String processID) {


        KieContainerResource containerResource = this.createContainer(id, container);
        if (containerResource.getMessages().size() == 1
                && containerResource.getMessages().get(0).getSeverity() != null
                && containerResource.getMessages().get(0).getSeverity().equals(Severity.INFO)) {
            ClassLoader localClassLoader = null;

            try {
                localClassLoader = Thread.currentThread()
                        .getContextClassLoader();
            } catch (ClassCastException e) {
                logger.info("GenericResource.runSession", e);
            }
            try {
                KieContainerInstance kci = registry.getContainer(id);
                Set<Class<?>> classes = kci.getExtraClasses();

                Class foundClass = this.getClassFromName(classes, className);
                if (foundClass != null) {
                    ClassLoader classLoader = foundClass.getClassLoader();
                    Class<?> theClass = classLoader.loadClass(className);
                    Thread.currentThread().setContextClassLoader(classLoader);
                    camelContext.setApplicationContextClassLoader(classLoader);
                    DroolsRouter droolsRouter = new DroolsRouter(camelContext, theClass, id, kci, processID);
                    camelContext.addRoutes(droolsRouter);
                    String serverName = System.getProperty("org.kie.server.id");
                    ContainerPojoPersist containerPojoPersist = containerRepository.findByServerNameAndContainerId(serverName, id);
                    if (containerPojoPersist == null) {
                        containerPojoPersist = new ContainerPojoPersist();
                        containerPojoPersist.setId(UUID.randomUUID().toString());
                        containerPojoPersist.setContainerId(id);
                        containerPojoPersist.setClassName(className);
                        containerPojoPersist.setProjectName(id);
                        containerPojoPersist.setServerName(serverName);
                        containerPojoPersist.setProcessID(processID);
                        containerRepository.save(containerPojoPersist);
                    } else {
                        containerPojoPersist.setContainerId(id);
                        containerPojoPersist.setClassName(className);
                        containerPojoPersist.setProjectName(id);
                        containerPojoPersist.setProcessID(processID);
                        containerPojoPersist.setServerName(serverName);
                        containerRepository.save(containerPojoPersist);
                    }

                }


            } catch (ClassNotFoundException e) {
                logger.error("createContainerWithRestBusinessService", e);
            } catch (Exception e) {
                logger.error("createContainerWithRestBusinessService", e);
            } finally {
                if (localClassLoader != null) {
                    Thread.currentThread().setContextClassLoader(localClassLoader);
                }
            }
        }
        return containerResource;


    }

    public KieContainerResource createContainer(String id, KieContainerResource container) {

        container.setContainerId(id);
        ServiceResponse<KieContainerResource> response = server.createContainer(id, container);
        if (response.getType().equals(KieServiceResponse.ResponseType.SUCCESS)) {
            return response.getResult();
        } else {
            Message m = new Message();
            List<String> texts = new ArrayList<>();
            texts.add(response.getMsg());
            List<Message> messagelist = new ArrayList<>();
            messagelist.add(m);
            m.setMessages(texts);
            container.setMessages(messagelist);
            return container;
        }

    }


    public ServiceResponse<KieContainerResource> activateContainer(String id) {
        ServiceResponse<KieContainerResource> response = server.activateContainer(id);

        return response;
    }


    public ServiceResponse<KieContainerResource> deactivateContainer(String id) {
        ServiceResponse<KieContainerResource> response = server.deactivateContainer(id);
        return response;
    }

    public ServiceResponse<KieContainerResource> getContainerInfo(String id) {
        ServiceResponse<KieContainerResource> result = server.getContainerInfo(id);
        return result;
    }

    public ServiceResponse<Void> disposeContainer(String id) {
        ServiceResponse<Void> result = server.disposeContainer(id);
        String serverName = System.getProperty("org.kie.server.id");
        ContainerPojoPersist element = containerRepository.findByServerNameAndContainerId(serverName, id);
        if (element != null) {
            containerRepository.delete(element);
        }
        return result;
    }


    public ServiceResponse<KieServerStateInfo> getServerState(@Context HttpHeaders headers) {
        ServiceResponse<KieServerStateInfo> result = server.getServerState();
        return result;
    }

    public boolean readycheck() {
        boolean result = server.isKieServerReady();
        return result;
    }


    public List<Message> healthcheck(boolean report) {
        List<Message> healthMessages = server.healthCheck(report);


        return healthMessages;
    }

    private Class getClassFromName(Set<Class<?>> classes, String name) {
        Class result = null;
        for (Class c : classes) {
            if (c.getCanonicalName() != null
                    && c.getCanonicalName().equals(name)) {
                result = c;
                break;
            }
        }
        return result;
    }
}
