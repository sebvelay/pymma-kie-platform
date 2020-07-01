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

import org.jboss.errai.security.shared.api.Group;
import org.jboss.errai.security.shared.api.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uberfire.commons.config.ConfigProperties;
import org.uberfire.ext.security.management.api.*;
import org.uberfire.ext.security.management.api.exception.SecurityManagementException;
import org.uberfire.ext.security.management.api.exception.UnsupportedServiceCapabilityException;
import org.uberfire.ext.security.management.impl.RoleManagerSettingsImpl;
import org.uberfire.ext.security.management.search.GroupsIdentifierRuntimeSearchEngine;
import org.uberfire.ext.security.management.search.IdentifierRuntimeSearchEngine;
import org.uberfire.ext.security.management.util.SecurityManagementUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Groups manager service provider implementation for Apache tomcat, when using default realm based on properties files.</p>
 * @since 0.8.0
 */
public class KiePlatformRoleManager implements RoleManager,ContextualManager {

    private static final Logger LOG = LoggerFactory.getLogger(KiePlatformRoleManager.class);



    IdentifierRuntimeSearchEngine<Group> groupsSearchEngine;

    public KiePlatformRoleManager() {
        this(new ConfigProperties(System.getProperties()));
    }



    public KiePlatformRoleManager(final Map<String, String> gitPrefs) {
        this(new ConfigProperties(gitPrefs));
    }

    public KiePlatformRoleManager(final ConfigProperties gitPrefs) {
      //  loadConfig(gitPrefs);
    }

    @Override
    public void initialize(UserSystemManager userSystemManager) throws Exception {
        groupsSearchEngine = new GroupsIdentifierRuntimeSearchEngine();
    }

    @Override
    public void destroy() throws Exception {

    }


    @Override
    public SearchResponse<Role> search(SearchRequest request) throws SecurityManagementException {
        throw new UnsupportedServiceCapabilityException(Capability.CAN_SEARCH_ROLES);
    }

    @Override
    public Role get(String identifier) throws SecurityManagementException {
        throw new UnsupportedServiceCapabilityException(Capability.CAN_READ_ROLE);
    }

    @Override
    public List<Role> getAll() throws SecurityManagementException {
        return null;
    }

    @Override
    public Role create(Role entity) throws SecurityManagementException {
        throw new UnsupportedServiceCapabilityException(Capability.CAN_ADD_ROLE);
    }

    @Override
    public Role update(Role entity) throws SecurityManagementException {
        throw new UnsupportedServiceCapabilityException(Capability.CAN_UPDATE_ROLE);
    }



    @Override
    public void delete(String... identifiers) throws SecurityManagementException {
        throw new UnsupportedServiceCapabilityException(Capability.CAN_DELETE_ROLE);
    }

    @Override
    public RoleManagerSettings getSettings() {
        final Map<Capability, CapabilityStatus> capabilityStatusMap = new HashMap<Capability, CapabilityStatus>(8);
        for (final Capability capability : SecurityManagementUtils.ROLES_CAPABILITIES) {
            capabilityStatusMap.put(capability,
                                    getCapabilityStatus(capability));
        }
        return new RoleManagerSettingsImpl(capabilityStatusMap);
    }

    protected CapabilityStatus getCapabilityStatus(Capability capability) {
        /**
        if (capability != null) {
            switch (capability) {
                case CAN_SEARCH_ROLES:
                case CAN_ADD_ROLE:
                case CAN_UPDATE_ROLE:
                case CAN_READ_ROLE:
                case CAN_DELETE_ROLE:
                    return CapabilityStatus.ENABLED;
            }
        }
         **/
        return CapabilityStatus.UNSUPPORTED;
    }


}
