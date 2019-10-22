package org.chtijbug.drools.proxy;

import org.chtijbug.drools.proxy.persistence.model.ContainerPojoPersist;
import org.chtijbug.drools.proxy.persistence.repository.ContainerRepository;
import org.chtijbug.drools.proxy.persistence.repository.ContainerRuntimeRepository;
import org.chtijbug.drools.proxy.service.KieServiceCommon;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieServerConfig;
import org.kie.server.api.model.ReleaseId;
import org.kie.server.services.impl.storage.KieServerState;
import org.kie.server.services.impl.storage.KieServerStateRepository;
import org.kie.server.services.impl.storage.KieServerStateRepositoryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class PlatfomKieServerStateRepository implements KieServerStateRepository {

    private static final Logger logger = LoggerFactory.getLogger(PlatfomKieServerStateRepository.class);

    private ContainerRepository containerRepository;

    private ContainerRuntimeRepository containerRuntimeRepository;

    private KieServiceCommon kieServiceCommon;

    private KieServerState kieServerState = new KieServerState();

    private String hostName;

    public PlatfomKieServerStateRepository(ContainerRepository containerRepository, ContainerRuntimeRepository containerRuntimeRepository,KieServiceCommon kieServiceCommon) {
        this.containerRepository = containerRepository;
        this.containerRuntimeRepository=containerRuntimeRepository;
        this.kieServiceCommon = kieServiceCommon;
        KieServerConfig config = new KieServerConfig();
        KieServerStateRepositoryUtils.populateWithSystemProperties(config);
        kieServerState.setConfiguration(config);
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            hostName = inetAddress.getHostName();
        } catch (UnknownHostException e) {
            logger.info("initCamelBusinessRoutes.getLocalHost", e);
        }
    }

    @Override
    public synchronized void store(String s, KieServerState kieServerState) {
        System.out.println("coucou");
    }

    @Override
    public KieServerState load(String serverID) {

        String serverName = KieServiceCommon.getKieServerID();

        List<ContainerPojoPersist> containers = containerRepository.findByServerName(serverName);
        for (ContainerPojoPersist element : containers) {
            KieContainerResource newContainer = new KieContainerResource();
            newContainer.setContainerId(element.getContainerId());
            newContainer.setReleaseId(new ReleaseId());
            newContainer.getReleaseId().setArtifactId(element.getArtifactId());
            newContainer.getReleaseId().setGroupId(element.getGroupId());
            newContainer.getReleaseId().setVersion(element.getVersion());
            kieServerState.getContainers().add(newContainer);

        }


        return kieServerState;
    }
}
