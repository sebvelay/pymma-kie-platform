package org.chtijbug.drools.proxy.persistence.repository;

import org.chtijbug.drools.proxy.persistence.json.KeyProject;
import org.chtijbug.drools.proxy.persistence.model.ProjectPersist;
import org.chtijbug.drools.proxy.persistence.model.RuntimePersist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends MongoRepository<ProjectPersist, String> {

    public ProjectPersist findByProjectName(KeyProject projectName);
    public ProjectPersist findByDeploymentName(String deploymentName);
    public List<ProjectPersist> findByServerName(String serverName);
    public List<ProjectPersist> findByServerNameAndDeploymentName(String serverName,String deploymentName);

}
