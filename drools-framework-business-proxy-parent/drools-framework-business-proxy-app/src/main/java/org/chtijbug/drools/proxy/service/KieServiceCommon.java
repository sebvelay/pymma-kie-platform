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

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.chtijbug.drools.proxy.PlatfomKieServerStateRepository;
import org.chtijbug.drools.proxy.camel.DroolsRouter;
import org.chtijbug.drools.proxy.persistence.model.ContainerPojoPersist;
import org.chtijbug.drools.proxy.persistence.model.ContainerRuntimePojoPersist;
import org.chtijbug.drools.proxy.persistence.model.RuntimePersist;
import org.chtijbug.drools.proxy.persistence.repository.ContainerRepository;
import org.chtijbug.drools.proxy.persistence.repository.ContainerRuntimeRepository;
import org.chtijbug.drools.proxy.persistence.repository.ReactiveContainerRuntimeRepository;
import org.chtijbug.drools.proxy.persistence.repository.RuntimeRepository;
import org.chtijbug.kieserver.services.drools.DroolsChtijbugKieServerExtension;
import org.chtijbug.kieserver.services.drools.DroolsChtijbugRulesExecutionService;
import org.kie.server.api.model.*;
import org.kie.server.services.api.KieServerExtension;
import org.kie.server.services.api.KieServerRegistry;
import org.kie.server.services.impl.KieContainerInstanceImpl;
import org.kie.server.services.impl.KieServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Service("kieService")
public class KieServiceCommon {


    private static final Logger logger = LoggerFactory.getLogger(KieServiceCommon.class);
    @Autowired
    CamelContext camelContext;

    Disposable subscription;


    private KieServerImpl server;

    private KieServerRegistry registry;

    private DroolsChtijbugRulesExecutionService droolsChtijbugRulesExecutionService = null;
    private DroolsChtijbugKieServerExtension droolsChtijbugKieServerExtension;
    @Inject
    private ContainerRepository containerRepository;
    @Inject
    private RuntimeRepository runtimeRepository;
    @Inject
    private ContainerRuntimeRepository containerRuntimeRepository;
    @Inject
    private ReactiveContainerRuntimeRepository reactiveContainerRuntimeRepository;
    @Value("${server.port}")
    private int serverPort;
    @Value("${runtime.port:-1}")
    private int runtimePort;
    @Value("${runtime.server:none}")
    private String runtimeServer;
    @Autowired
    private ApplicationContext appContext;
    
    private String hostName = "localhost";
    private Map<String, DroolsRouter> routes = new HashMap<>();

    @Value("${spring.data.mongodb.database}")
    private String databaseName;
    
    @Inject
    private MongoClient mongoClient;

    public KieServiceCommon() {

    }

    public static String getKieServerID() {
        return System.getProperty("org.kie.server.id");
    }

    @PostConstruct
    private void initCamelBusinessRoutes() {

        this.server = new KieServerImpl(new PlatfomKieServerStateRepository(containerRepository, containerRuntimeRepository, this));
        this.server.init();

        List<KieServerExtension> serverExtensions = this.server.getServerExtensions();
        if (registry == null || droolsChtijbugKieServerExtension == null) {
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
        }

        String serverName = KieServiceCommon.getKieServerID();

        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            hostName = inetAddress.getHostName();
        } catch (UnknownHostException e) {
            logger.info("initCamelBusinessRoutes.getLocalHost", e);
        }
        List<RuntimePersist> itIsMes;
        if (runtimePort == -1) {
            itIsMes = runtimeRepository.findByServerNameAndHostname(serverName, hostName);
        } else {
            itIsMes = runtimeRepository.findByServerName(serverName);
        }
        RuntimePersist runtimePersist;
        ServiceResponse<KieServerInfo> result = server.getInfo();
        String version = result.getResult().getVersion();
        if (itIsMes.isEmpty()) {
            runtimePersist = new RuntimePersist(serverName, version, hostName,
                    String.valueOf(serverPort), null,
                    hostName, RuntimePersist.STATUS.UP.toString());
            if (runtimePort != -1) {
                runtimePersist.setHostname(runtimeServer);
                runtimePersist.setServerPort(String.valueOf(runtimePort));
            }
            String isSwarm = System.getProperty("org.kie.server.swarm");
            String swarmPort=System.getProperty("org.kie.server.swarm.port");
            if ("1".equals(isSwarm)) {
                if (swarmPort != null &&
                        swarmPort.length() > 0) {
                    runtimePersist.setServerUrl("http://" + serverName + ":" + swarmPort);
                } else {
                    runtimePersist.setServerUrl("http://" + serverName + ":" + serverPort);
                }

            } else {
                if (runtimePort == -1) {
                    runtimePersist.setServerUrl("http://" + hostName + ":" + serverPort);
                } else {
                    runtimePersist.setServerUrl("http://" + runtimeServer + ":" + runtimePort);
                }
            }
            runtimeRepository.save(runtimePersist);
        } else {
            runtimePersist = itIsMes.get(0);
            runtimePersist.setStatus(RuntimePersist.STATUS.UP.toString());
            runtimeRepository.save(runtimePersist);
        }

        try {
            for (KieContainerResource kieContainerResource : this.server.getServerState().getResult().getContainers()) {
                this.createContainer(kieContainerResource.getContainerId(), kieContainerResource);
                ContainerPojoPersist container = containerRepository.findByServerNameAndContainerId(serverName, kieContainerResource.getContainerId());
                if (container != null) {
                    ContainerRuntimePojoPersist containerRuntimePojoPersist = containerRuntimeRepository.findByServerNameAndContainerIdAndHostname(serverName, kieContainerResource.getContainerId(), hostName);
                    if (containerRuntimePojoPersist == null) {
                        containerRuntimePojoPersist = new ContainerRuntimePojoPersist();
                        containerRuntimePojoPersist.setContainerId(container.getContainerId());
                        containerRuntimePojoPersist.setServerName(serverName);
                        containerRuntimePojoPersist.setHostname(hostName);
                        containerRuntimePojoPersist.setStatus(ContainerRuntimePojoPersist.STATUS.UP.name());
                        containerRuntimePojoPersist = containerRuntimeRepository.save(containerRuntimePojoPersist);
                    }
                    Flux<ContainerRuntimePojoPersist> streamToDeploy = reactiveContainerRuntimeRepository.findByServerNameAndHostname(containerRuntimePojoPersist.getServerName(),containerRuntimePojoPersist.getHostname());
                    MongoDatabase db = mongoClient.getDatabase(databaseName);
                  //  db.runCommand("{ convertToCapped: 'ContainerRuntimePojoPersist', size: 9128 }");


                    this.subscription = streamToDeploy.subscribe(elementToDeploy -> {
                                try {
                                    if (ContainerRuntimePojoPersist.STATUS.TODEPLOY.name().equals(elementToDeploy.getStatus())) {
                                        this.updateConfigToDeploy(container, elementToDeploy);
                                    }
                                } catch (Exception e) {
                                    logger.error("updateConfigToDeploy",e);
                                }
                            }
                    );
                    this.initCamelBusinessRoute(container);
                }
            }
        } catch (Exception e) {
            logger.info("initCamelBusinessRoutes", e);
        }
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                String serverName = KieServiceCommon.getKieServerID();
                List<RuntimePersist> itIsMes = runtimeRepository.findByServerNameAndHostname(serverName, hostName);
                if (itIsMes.size() == 1) {
                    RuntimePersist runtimePersist = itIsMes.get(0);
                    runtimePersist.setStatus(RuntimePersist.STATUS.DOWN.toString());
                    runtimeRepository.delete(runtimePersist);
                }
                List<ContainerRuntimePojoPersist> ccc = containerRuntimeRepository.findByServerNameAndHostname(serverName, hostName);
                if (!ccc.isEmpty()) {
                    containerRuntimeRepository.deleteAll(ccc);
                }
            }
        });


    }

    public void setTimeStamp() {
        String serverName = KieServiceCommon.getKieServerID();
        List<RuntimePersist> itIsMes = runtimeRepository.findByServerNameAndHostname(serverName, hostName);
        if (itIsMes.size() == 1) {
            RuntimePersist runtimePersist = itIsMes.get(0);
            runtimePersist.setTimeStamp(new Date());
            runtimeRepository.save(runtimePersist);
        }
    }

    public void deleteCamelBusinessRoute(String containerId) throws Exception {
        if (routes.containsKey(containerId)) {
            DroolsRouter routeToDelete = routes.get(containerId);
            String target = routeToDelete.getRouteCollection().getRoutes().get(0).getId();
            for (Route route : camelContext.getRoutes()) {
                if (route.getId().equals(target)) {
                    camelContext.stopRoute(route.getId());
                    camelContext.removeRoute(route.getId());
                }
            }
        }
    }

    public void initCamelBusinessRoute(ContainerPojoPersist container) throws Exception {
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
                Class<Object> foundClass = this.getClassFromName(classes, className);
                if (foundClass != null) {
                    ClassLoader classLoader = foundClass.getClassLoader();
                    Class<?> theClass = classLoader.loadClass(className);
                    camelContext.setApplicationContextClassLoader(classLoader);
                    Thread.currentThread().setContextClassLoader(classLoader);
                    String projectName = container.getContainerId();
                    String processId = container.getProcessID();
                    this.deleteCamelBusinessRoute(projectName);
                    DroolsRouter droolsRouter = new DroolsRouter(camelContext, theClass, projectName, kieContainerInstance, processId);
                    camelContext.addRoutes(droolsRouter);
                    routes.put(containerId, droolsRouter);
                }
            } finally {
                if (localClassLoader != null) {
                    Thread.currentThread().setContextClassLoader(localClassLoader);
                }
            }
        }
    }

    public DroolsChtijbugRulesExecutionService getDroolsChtijbugRulesExecutionService() {
        return droolsChtijbugRulesExecutionService;
    }

    public KieServerRegistry getRegistry() {
        return registry;
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

    public void updateConfigToDeploy(ContainerPojoPersist container, ContainerRuntimePojoPersist containerRuntimePojoPersist) throws Exception {
        this.disposeContainer(container.getContainerId());
        KieContainerResource newContainer = new KieContainerResource();
        newContainer.setContainerId(container.getContainerId());
        newContainer.setReleaseId(new ReleaseId());
        newContainer.getReleaseId().setArtifactId(container.getArtifactId());
        newContainer.getReleaseId().setGroupId(container.getGroupId());
        newContainer.getReleaseId().setVersion(container.getVersion());
        this.createContainer(container.getContainerId(), newContainer);
        this.initCamelBusinessRoute(container);
        containerRuntimePojoPersist.setStatus(ContainerRuntimePojoPersist.STATUS.UP.toString());
        containerRuntimeRepository.save(containerRuntimePojoPersist);

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
                String serverName = KieServiceCommon.getKieServerID();
                ContainerPojoPersist containerPojoPersist = containerRepository.findByServerNameAndContainerId(serverName, id);
                if (containerPojoPersist == null) {
                    containerPojoPersist = new ContainerPojoPersist();
                    containerPojoPersist.setId(UUID.randomUUID().toString());
                    containerPojoPersist.setContainerId(id);
                    containerPojoPersist.setClassName(className);
                    containerPojoPersist.setProjectName(id);
                    containerPojoPersist.setServerName(serverName);
                    containerPojoPersist.setProcessID(processID);

                } else {
                    containerPojoPersist.setContainerId(id);
                    containerPojoPersist.setClassName(className);
                    containerPojoPersist.setProjectName(id);
                    containerPojoPersist.setProcessID(processID);
                    containerPojoPersist.setServerName(serverName);

                }
                this.initCamelBusinessRoute(containerPojoPersist);
                containerRepository.save(containerPojoPersist);

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
         return server.activateContainer(id);
    }


    public ServiceResponse<KieContainerResource> deactivateContainer(String id) {
         return server.deactivateContainer(id);
    }

    public ServiceResponse<KieContainerResource> getContainerInfo(String id) {
        return server.getContainerInfo(id);
    }

    public ServiceResponse<Void> disposeContainer(String id) {
        ServiceResponse<Void> result = server.disposeContainer(id);
        String serverName = KieServiceCommon.getKieServerID();
        ContainerRuntimePojoPersist element = containerRuntimeRepository.findByServerNameAndContainerIdAndHostname(serverName, id, hostName);
        if (element != null) {
            containerRuntimeRepository.delete(element);
        }
        return result;
    }


    public ServiceResponse<KieServerStateInfo> getServerState(@Context HttpHeaders headers) { ;
        return server.getServerState();
    }

    public boolean readycheck() {
        return server.isKieServerReady();
    }


    public List<Message> healthcheck(boolean report) {
       return server.healthCheck(report);
    }

    private Class<Object> getClassFromName(Set<Class<?>> classes, String name) {
        Class<Object> result = null;
        for (Class c : classes) {
            if (c.getCanonicalName() != null
                    && c.getCanonicalName().equals(name)) {
                result = c;
                break;
            }
        }
        return result;
    }

    public void close() {
        this.subscription.dispose();
    }
}
