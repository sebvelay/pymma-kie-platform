package org.chtijbug.guvnor.uberfire.security;


import org.uberfire.ext.security.management.UberfireRoleManager;
import org.uberfire.ext.security.management.api.GroupManager;
import org.uberfire.ext.security.management.api.UserManager;
import org.uberfire.ext.security.management.service.AbstractUserManagementService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Dependent
@Named(value = "TomcatUserManagementService")
public class KiePlatformSecurityService extends AbstractUserManagementService {

    KiePlatformUserManager userManager;
    KiePlatformGroupManager groupManager;

    @Inject
    public KiePlatformSecurityService(final KiePlatformUserManager userManager,
                                       final KiePlatformGroupManager groupManager,
                                       final @Named("uberfireRoleManager") UberfireRoleManager roleManager) {
        super(roleManager);
        this.userManager = userManager;
        this.groupManager = groupManager;
    }
    @Override
    public UserManager users() {
        return new KiePlatformUserManager();
    }

    @Override
    public GroupManager groups() {
        return new KiePlatformGroupManager();
    }
}
