package org.chtijbug.drools.proxy;

import org.chtijbug.drools.proxy.persistence.model.ContainerPojoPersist;
import org.chtijbug.drools.proxy.persistence.repository.ContainerRepository;
import org.chtijbug.drools.proxy.service.KieServiceCommon;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieServerConfig;
import org.kie.server.api.model.ReleaseId;
import org.kie.server.services.impl.storage.KieServerState;
import org.kie.server.services.impl.storage.KieServerStateRepository;
import org.kie.server.services.impl.storage.KieServerStateRepositoryUtils;

import java.util.List;

public class PlatfomKieServerStateRepository implements KieServerStateRepository {


    private ContainerRepository containerRepository;

    private KieServiceCommon kieServiceCommon;

    private KieServerState kieServerState = new KieServerState();

    public PlatfomKieServerStateRepository(ContainerRepository containerRepository, KieServiceCommon kieServiceCommon) {
        this.containerRepository = containerRepository;
        this.kieServiceCommon = kieServiceCommon;
        KieServerConfig config = new KieServerConfig();
        KieServerStateRepositoryUtils.populateWithSystemProperties(config);
        kieServerState.setConfiguration(config);
    }

    @Override
    public synchronized void store(String s, KieServerState kieServerState) {
        System.out.println("coucou");
    }

    @Override
    public KieServerState load(String serverID) {

        String serverName = KieServiceCommon.getKieServerID();

        List<ContainerPojoPersist> containers = containerRepository.findByServerNameAndStatus(serverName, ContainerPojoPersist.STATUS.UP.toString());
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
