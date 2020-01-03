/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.chtijbug.guvnor.uberfire.security;

import org.jboss.errai.security.shared.api.identity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uberfire.commons.config.ConfigProperties;
import org.uberfire.ext.security.management.api.*;
import org.uberfire.ext.security.management.api.exception.SecurityManagementException;
import org.uberfire.ext.security.management.api.exception.UnsupportedServiceCapabilityException;
import org.uberfire.ext.security.management.impl.UserManagerSettingsImpl;
import org.uberfire.ext.security.management.search.IdentifierRuntimeSearchEngine;
import org.uberfire.ext.security.management.search.UsersIdentifierRuntimeSearchEngine;
import org.uberfire.ext.security.management.util.SecurityManagementUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>Users manager service provider implementation for Apache tomcat, when using default realm based on properties files.</p>
 *
 * @since 0.8.0
 */
public class KiePlatformUserManager  implements UserManager, ContextualManager {

    private static final Logger LOG = LoggerFactory.getLogger(KiePlatformUserManager.class);

    UserSystemManager userSystemManager;
    IdentifierRuntimeSearchEngine<User> usersSearchEngine;



    public KiePlatformUserManager() {
        this(new ConfigProperties(System.getProperties()));
    }

    public KiePlatformUserManager(final Map<String, String> gitPrefs) {
        this(new ConfigProperties(gitPrefs));
    }

    public KiePlatformUserManager(final ConfigProperties gitPrefs) {
        //loadConfig(gitPrefs);
    }



    @Override
    public void initialize(final UserSystemManager userSystemManager) throws Exception {
        this.userSystemManager = userSystemManager;
        usersSearchEngine = new UsersIdentifierRuntimeSearchEngine();
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public SearchResponse<User> search(SearchRequest request) throws SecurityManagementException {
        throw new UnsupportedServiceCapabilityException(Capability.CAN_SEARCH_USERS);
    }

    @Override
    public User get(String identifier) throws SecurityManagementException {
        throw new UnsupportedServiceCapabilityException(Capability.CAN_READ_USER);
    }

    @Override
    public User create(User entity) throws SecurityManagementException {
        throw new UnsupportedServiceCapabilityException(Capability.CAN_ADD_USER);
    }

    @Override
    public User update(User entity) throws SecurityManagementException {
        throw new UnsupportedServiceCapabilityException(Capability.CAN_UPDATE_USER);
    }

    @Override
    public void delete(String... identifiers) throws SecurityManagementException {
        throw new UnsupportedServiceCapabilityException(Capability.CAN_DELETE_USER);

    }

    @Override
    public UserManagerSettings getSettings() {
        final Map<Capability, CapabilityStatus> capabilityStatusMap = new HashMap<Capability, CapabilityStatus>(8);
        for (final Capability capability : SecurityManagementUtils.USERS_CAPABILITIES) {
            capabilityStatusMap.put(capability,
                                    getCapabilityStatus(capability));
        }
        return new UserManagerSettingsImpl(capabilityStatusMap,
                                           null);
    }

    @Override
    public void assignGroups(String username,
                             Collection<String> groups) throws SecurityManagementException {
        Set<String> userRoles = SecurityManagementUtils.rolesToString(SecurityManagementUtils.getRoles(userSystemManager,
                                                                                                       username));
        userRoles.addAll(groups);
        doAssignGroups(username,
                       userRoles);
    }

    @Override
    public void assignRoles(String username,
                            Collection<String> roles) throws SecurityManagementException {
        throw new UnsupportedServiceCapabilityException(Capability.CAN_ASSIGN_ROLES);
    }

    private void doAssignGroups(String username,
                                Collection<String> ids) throws SecurityManagementException {

    }

    @Override
    public void changePassword(String username,
                               String newPassword) throws SecurityManagementException {
        throw new UnsupportedServiceCapabilityException(Capability.CAN_CHANGE_PASSWORD);

    }

    protected CapabilityStatus getCapabilityStatus(Capability capability) {
        /**
        if (capability != null) {
            switch (capability) {
                case CAN_SEARCH_USERS:
                case CAN_ADD_USER:
                case CAN_UPDATE_USER:
                case CAN_DELETE_USER:
                case CAN_READ_USER:
                case CAN_MANAGE_ATTRIBUTES:
                case CAN_ASSIGN_GROUPS:

                case CAN_ASSIGN_ROLES:
                case CAN_CHANGE_PASSWORD:
                    return CapabilityStatus.ENABLED;
            }
        }
        **/
        return CapabilityStatus.UNSUPPORTED;
    }
}
