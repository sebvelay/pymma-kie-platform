package org.chtijbug.drools.reverseproxy.service;

import com.github.mkopylec.charon.configuration.MappingProperties;
import org.chtijbug.drools.proxy.persistence.model.ProjectPersist;
import org.chtijbug.drools.proxy.persistence.model.RuntimePersist;
import org.chtijbug.drools.proxy.persistence.repository.ProjectRepository;
import org.chtijbug.drools.proxy.persistence.repository.RuntimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service("updateService")
public class UpdateService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private RuntimeRepository runtimeRepository;

    private Map<String, RuntimePersist> runtimes = new HashMap<>();
    private Map<String, ProjectPersist> projects = new HashMap<>();

    private Boolean toUpdate = false;

    private List<MappingProperties> mappings=new ArrayList<>();

    public Boolean getToUpdate() {
        return toUpdate;
    }

    public void updateConfig() {
        if (this.toUpdate==false) {
            this.toUpdate = isToUpdate();
            if (this.toUpdate==true){
                generateMappings();
            }
        }
    }

    private boolean isToUpdate() {
        boolean result = false;
        runtimes.clear();
        List<RuntimePersist> runtimePersists = runtimeRepository.findAll();
        for (RuntimePersist runtimePersist : runtimePersists) {
            runtimes.put(runtimePersist.getServerName(), runtimePersist.duplicate());
        }
        List<ProjectPersist> projectPersists = projectRepository.findAll();
        List<String> projectOk = new ArrayList<>();
        for (ProjectPersist projectPersist : projectPersists) {
            if (projectPersist.getServerNames().size() > 0) {
                projectOk.add(projectPersist.getContainerID());
                if (projects.containsKey(projectPersist.getContainerID()) == false) {
                    return true;
                }

                // if a new project is not in the already displayed

                List<String> list1 = projectPersist.getServerNames();
                int l1 = list1.size();
                ProjectPersist run2 = projects.get(projectPersist.getContainerID());
                List<String> list2 = run2.getServerNames();
                if (list2.size() != list1.size()) {
                    return true;
                }
                list1.retainAll(list2);
                if (list2.size() != list1.size()) {
                    return true;
                }

            }
        }
        // An existing project in the reverse is no more to be displayed
        for (String goodId : projects.keySet()) {
            if (projectOk.contains(goodId) == false) {
                return true;
            }
        }

        return result;
    }

    public List<MappingProperties> retrievePath() {
        this.toUpdate=false;
        return mappings;
    }
    private void generateMappings(){
        projects.clear();
        List<MappingProperties> paths = new ArrayList<>();
        Collection<ProjectPersist> projectPersists = projectRepository.findAll();
        for (ProjectPersist projectPersist : projectPersists) {
            if (projectPersist.getServerNames().size() > 0) {
                projects.put(projectPersist.getContainerID(), projectPersist.duplicate());
                MappingProperties mappingProperties2 = new MappingProperties();
                for (String serverName : projectPersist.getServerNames()) {
                    RuntimePersist runtimePersist = runtimes.get(serverName);
                    String hostName = runtimePersist.getHostname() + "/api/" + projectPersist.getContainerID();
                    mappingProperties2.getDestinations().add(hostName);
                }
                paths.add(mappingProperties2);
                mappingProperties2.setName(projectPersist.getContainerID());
                mappingProperties2.setPath("/" + projectPersist.getContainerID());
                mappingProperties2.getCustomConfiguration().put("connect", 2000);
                mappingProperties2.getCustomConfiguration().put("read", 2000);
                mappingProperties2.setStripPath(true);
            }

        }
        mappings.clear();
        mappings.addAll(paths);
    }

    @PostConstruct
    public void initConfig() {
        List<RuntimePersist> runtimePersists = runtimeRepository.findAll();
        for (RuntimePersist runtimePersist : runtimePersists) {
            runtimes.put(runtimePersist.getServerName(), runtimePersist);
        }
        List<ProjectPersist> projectPersists = projectRepository.findAll();
        for (ProjectPersist projectPersist : projectPersists) {
            if (projectPersist.getServerNames().size() > 0) {
                projects.put(projectPersist.getContainerID(), projectPersist.duplicate());
            }
        }
        generateMappings();
    }

}
