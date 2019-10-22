package org.chtijbug.drools.reverseproxy.service;

import com.github.mkopylec.charon.configuration.MappingProperties;
import org.chtijbug.drools.proxy.persistence.model.ProjectPersist;
import org.chtijbug.drools.proxy.persistence.model.RuntimePersist;
import org.chtijbug.drools.proxy.persistence.repository.ProjectRepository;
import org.chtijbug.drools.proxy.persistence.repository.RuntimeRepository;
import org.chtijbug.drools.reverseproxy.mappings.CustomMappingsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service("updateService")
public class UpdateService {
    private static final Logger logger = LoggerFactory.getLogger(UpdateService.class);

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private RuntimeRepository runtimeRepository;

    private Map<String, RuntimePersist> runtimes = new HashMap<>();
    private Map<String, ProjectPersist> projects = new HashMap<>();

    private Boolean toUpdate = true;

    private List<MappingProperties> mappings=new ArrayList<>();

    private Map<String,MappingProperties>   mappingPropertiesMap = new HashMap<>();
    @Autowired
    private CustomMappingsProvider customMappingsProvider;

    public Boolean getToUpdate() {
        return toUpdate;
    }

    public void updateConfig() {
        /**
        if (this.toUpdate==false) {
            this.toUpdate = isToUpdate();
            if (this.toUpdate==true){
                generateMappings();
            }
        }
         **/
        generateMappings();
    }

    private boolean isToUpdate() {
        boolean result = false;
        runtimes.clear();
        List<RuntimePersist> runtimePersists = runtimeRepository.findAll();
        Map<String, String> urlMap = new HashMap<>();
        for (RuntimePersist runtimePersist : runtimePersists) {
            if (urlMap.containsKey(runtimePersist.getServerName()) == false) {
                urlMap.put(runtimePersist.getServerName(), runtimePersist.getServerUrl());
                runtimes.put(runtimePersist.getServerName(), runtimePersist.duplicate());
            }
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
        mappingPropertiesMap.clear();
        List<MappingProperties> paths = new ArrayList<>();
        Collection<ProjectPersist> projectPersists = projectRepository.findAll();
        Map<String, String> urlMap = new HashMap<>();
        List<RuntimePersist> runtimePersists = runtimeRepository.findAll();
        for (RuntimePersist runtimePersist : runtimePersists) {
            if (urlMap.containsKey(runtimePersist.getServerName()) == false) {
                urlMap.put(runtimePersist.getServerName(), runtimePersist.getServerUrl());
                runtimes.put(runtimePersist.getServerName(), runtimePersist.duplicate());
            }
        }
        for (ProjectPersist projectPersist : projectPersists) {
            if (projectPersist.getServerNames().size() > 0) {
                projects.put(projectPersist.getContainerID(), projectPersist.duplicate());
                MappingProperties mappingProperties2 = new MappingProperties();
                String servList=null;
                for (String serverName : projectPersist.getServerNames()) {
                    RuntimePersist runtimePersist = runtimes.get(serverName);
                    if (runtimePersist!= null) {
                        String hostName = runtimePersist.getServerUrl() + "/api/" + projectPersist.getContainerID();
                        mappingProperties2.getDestinations().add(hostName);
                        if (servList==null){
                            servList=serverName;
                        }else{
                            servList=servList+":"+serverName;
                        }

                    }
                }
                mappingProperties2.setName(projectPersist.getContainerID());
                mappingProperties2.setPath("/" + projectPersist.getContainerID());
                mappingProperties2.getCustomConfiguration().put("connect", 2000);
                mappingProperties2.getCustomConfiguration().put("read", 2000);
                mappingProperties2.setStripPath(true);
                if ( mappingProperties2.getDestinations().size()>0) {
                    mappingPropertiesMap.put(mappingProperties2.getPath(),mappingProperties2);
                    paths.add(mappingProperties2);
                    logger.info("Project "+projectPersist.getContainerID()+" defined on servers - "+mappingProperties2.getDestinations().toString());

                }else{
                    logger.error("Project "+projectPersist.getContainerID()+" defined on non existing server");
                }


            }

        }
        mappings.clear();
        mappings.addAll(paths);
        this.customMappingsProvider.setMappingPropertiesMap(mappingPropertiesMap);
    }

    @PostConstruct
    public void initConfig() {
        generateMappings();
    }

}
