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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uberfire.commons.config.ConfigProperties;
import org.uberfire.ext.security.management.api.*;
import org.uberfire.ext.security.management.api.exception.SecurityManagementException;
import org.uberfire.ext.security.management.api.exception.UnsupportedServiceCapabilityException;
import org.uberfire.ext.security.management.impl.GroupManagerSettingsImpl;
import org.uberfire.ext.security.management.search.GroupsIdentifierRuntimeSearchEngine;
import org.uberfire.ext.security.management.search.IdentifierRuntimeSearchEngine;
import org.uberfire.ext.security.management.util.SecurityManagementUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Groups manager service provider implementation for Apache tomcat, when using default realm based on properties files.</p>
 * @since 0.8.0
 */
public class KiePlatformGroupManager  implements GroupManager, ContextualManager {

    private static final Logger LOG = LoggerFactory.getLogger(KiePlatformGroupManager.class);



    IdentifierRuntimeSearchEngine<Group> groupsSearchEngine;

    public KiePlatformGroupManager() {
        this(new ConfigProperties(System.getProperties()));
    }


    public KiePlatformGroupManager(final Map<String, String> gitPrefs) {
        this(new ConfigProperties(gitPrefs));
    }

    public KiePlatformGroupManager(final ConfigProperties gitPrefs) {
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
    public SearchResponse<Group> search(SearchRequest request) throws SecurityManagementException {
        throw new UnsupportedServiceCapabilityException(Capability.CAN_SEARCH_GROUPS);
    }

    @Override
    public Group get(String identifier) throws SecurityManagementException {
        throw new UnsupportedServiceCapabilityException(Capability.CAN_READ_GROUP);
    }

    @Override
    public List<Group> getAll() throws SecurityManagementException {
        return null;
    }

    @Override
    public Group create(Group entity) throws SecurityManagementException {
        throw new UnsupportedServiceCapabilityException(Capability.CAN_ADD_GROUP);
    }

    @Override
    public Group update(Group entity) throws SecurityManagementException {
        throw new UnsupportedServiceCapabilityException(Capability.CAN_UPDATE_GROUP);
    }

    @Override
    public void delete(String... identifiers) throws SecurityManagementException {
        throw new UnsupportedServiceCapabilityException(Capability.CAN_DELETE_GROUP);
    }

    @Override
    public GroupManagerSettings getSettings() {
        final Map<Capability, CapabilityStatus> capabilityStatusMap = new HashMap<Capability, CapabilityStatus>(8);
        for (final Capability capability : SecurityManagementUtils.GROUPS_CAPABILITIES) {
            capabilityStatusMap.put(capability,
                                    getCapabilityStatus(capability));
        }
        return new GroupManagerSettingsImpl(capabilityStatusMap,
                                            true);
    }

    protected CapabilityStatus getCapabilityStatus(Capability capability) {
        /**
        if (capability != null) {
            switch (capability) {
                case CAN_SEARCH_GROUPS:
                case CAN_ADD_GROUP:
                case CAN_UPDATE_GROUP:
                case CAN_READ_GROUP:
                case CAN_DELETE_GROUP:
                    return CapabilityStatus.ENABLED;
            }
        }
         **/
        return CapabilityStatus.UNSUPPORTED;
    }

    @Override
    public void assignUsers(String name,
                            Collection<String> users) throws SecurityManagementException {
        throw new UnsupportedServiceCapabilityException(Capability.CAN_ASSIGN_GROUPS);
    }
}
