package org.chtijbug.guvnor.uberfire.security;


import org.uberfire.ext.security.management.api.GroupManager;
import org.uberfire.ext.security.management.api.RoleManager;
import org.uberfire.ext.security.management.api.UserManagementService;
import org.uberfire.ext.security.management.api.UserManager;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Dependent
@Named(value = "PymmaKieSecurityService")
public class KiePlatformSecurityService implements UserManagementService {

    KiePlatformUserManager userManager;
    KiePlatformGroupManager groupManager;
    KiePlatformRoleManager roleManager;

    private String connectionString;
    private String databaseName;
   // private MongoClient mongoClient;
   // private CodecRegistry pojoCodecRegistry;
   //private MongoDatabase database;

    public KiePlatformSecurityService() {
        System.out.println("KiePlatformSecurityService initialized with databaseName = " + connectionString );
    }

    @Inject
    public KiePlatformSecurityService(KiePlatformUserManager userManager,
                                      KiePlatformGroupManager groupManager,
                                      KiePlatformRoleManager roleManager) {

        connectionString = System.getProperty("connectionString");
        databaseName=System.getProperty("name");
        System.out.println("KiePlatformSecurityService initialized with databaseName = " + connectionString );
        //mongoClient = MongoClients.create(connectionString);
        //pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        //        fromProviders(PojoCodecProvider.builder().automatic(true).build()));
       // database = mongoClient.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);
        System.out.println("All setup");
        this.userManager = userManager;
        this.groupManager = groupManager;
        this.roleManager = roleManager;

    }


    @Override
    public UserManager users() {
        return new KiePlatformUserManager();
    }

    @Override
    public GroupManager groups() {
        return new KiePlatformGroupManager();
    }

    @Override
    public RoleManager roles() {
        return new KiePlatformRoleManager();
    }
}
