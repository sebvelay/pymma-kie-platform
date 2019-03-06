package org.chtijbug.drools.indexer.service;


import org.apache.camel.CamelContext;
import org.chtijbug.drools.indexer.route.IndexerRoute;
import org.chtijbug.drools.proxy.persistence.model.RuntimePersist;
import org.chtijbug.drools.proxy.persistence.repository.RuntimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service("startRouteService")
public class StartRouteService {
    @Value("${kieserver.login}")
    private String login;
    @Value("${kieserver.password}")
    private String password;
    @Autowired
    CamelContext camelContext;
    @Autowired
    RuntimeRepository runtimeRepository;


    private Map<String,RuntimePersist> routes = new HashMap<>();
    private Map<String,IndexerRoute> routesCamel = new HashMap<>();



    public void updateConfig() throws Exception {
        for (RuntimePersist runtime : runtimeRepository.findAll()){
          if (routes.containsKey(runtime.getId())==false){
              IndexerRoute indexerRoute =new IndexerRoute(runtime.getId(),login,password,runtime.getSftpHost(),runtime.getSftpPort());
              camelContext.addRoutes(indexerRoute);
              routes.put(runtime.getSftpHost()+":"+runtime.getSftpPort(),runtime.duplicate());
              routesCamel.put(runtime.getSftpHost()+":"+runtime.getSftpPort(),indexerRoute);
          }else{
              RuntimePersist existingRoutes = routes.get(runtime.getId());
              if (!existingRoutes.getSftpHost().equals(runtime.getSftpHost())
                || !existingRoutes.getSftpPort().equals(runtime.getSftpPort())){
                  //routes must be reloaded
                  IndexerRoute routeToDelete=routesCamel.get(runtime.getId());
                  routesCamel.remove(runtime.getId());
                  camelContext.removeRoute(runtime.getId());
                  routes.remove(runtime.getId());

                  IndexerRoute indexerRoute =new IndexerRoute(runtime.getId(),login,password,runtime.getSftpHost(),runtime.getSftpPort());
                  camelContext.addRoutes(indexerRoute);
                  routes.put(runtime.getId(),runtime.duplicate());
                  routesCamel.put(runtime.getId(),indexerRoute);
              }
          }
        }
    }

    @PostConstruct
    public void constructIndexerRoute() throws Exception {
        for (RuntimePersist runtime : runtimeRepository.findAll()){
            IndexerRoute indexerRoute =new IndexerRoute(runtime.getId(),login,password,runtime.getSftpHost(),runtime.getSftpPort());
            camelContext.addRoutes(indexerRoute);

            routes.put(runtime.getId(),runtime.duplicate());
            routesCamel.put(runtime.getId(),indexerRoute);
        }
    }
}
