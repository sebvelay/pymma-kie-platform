package org.chtijbug.drools.proxy.persistence.repository;

import org.chtijbug.drools.proxy.persistence.json.KieProject;
import org.chtijbug.drools.proxy.persistence.model.ProjectPersist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends MongoRepository<ProjectPersist, String> {

    public ProjectPersist findByProjectName(KieProject projectName);
    public ProjectPersist findByDeploymentName(String deploymentName);
    public List<ProjectPersist> findByServerNamesIn(List<String> serverNames);
    public List<ProjectPersist> findByServerNamesInAndDeploymentName(List<String> serverNames,String deploymentName);

}
