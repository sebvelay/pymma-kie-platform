/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.chtijbug.drools.console.service.kieserver;

import org.kie.server.api.model.KieContainerResourceFilter;
import org.kie.server.api.model.KieContainerStatusFilter;
import org.kie.server.api.model.KieServerInfo;
import org.kie.server.api.model.ReleaseIdFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;

import static org.kie.server.api.rest.RestURI.CONTAINER_ID;


@RestController
@RequestMapping("/api/server")
public class KieServerRestImpl {

    @Value(value = "${org.kie.server.id}")
    private String kieserverID;

    @Value("${org.kie.server.controller}")
    private String kiewbUrl;

    @Value("${org.kie.server.location}")
    private String controlerLocation;

    public KieServerRestImpl() {
        // for now, if no server impl is passed as parameter, create one

    }
//@RequestHeader HttpHeaders headers
    @GetMapping(path="/{kieServerName}",produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public KieServerInfo getInfo(
            @PathVariable("kieServerName") String kieServerName) {
        KieServerInfo kieServerInfo = new KieServerInfo();
        kieServerInfo.setServerId(kieserverID);
        kieServerInfo.setLocation(controlerLocation);
        kieServerInfo.setName(kieserverID);
        kieServerInfo.setCapabilities(new ArrayList<>());
        kieServerInfo.getCapabilities().add("BRM");
        return kieServerInfo;
    }

    @GetMapping(path = "/{kieServerName}/containers",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response listContainers(@RequestHeader HttpHeaders headers,
                                   @PathVariable("kieServerName") String kieServerName,
                                   @RequestParam("groupId") String groupId,
                                   @RequestParam("artifactId") String artifactId,
                                   @RequestParam("version") String version,
                                   @RequestParam("status") String status) {
        ReleaseIdFilter releaseIdFilter = new ReleaseIdFilter.Builder()
                .groupId(groupId)
                .artifactId(artifactId)
                .version(version)
                .build();

        KieContainerStatusFilter statusFilter = KieContainerStatusFilter.parseFromNullableString(status);
        KieContainerResourceFilter containerFilter = new KieContainerResourceFilter(releaseIdFilter, statusFilter);
        return null;
    }


    @PutMapping(path = "/{kieServerName}/containers/{" + CONTAINER_ID + "}",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createContainer(@RequestHeader HttpHeaders headers,
                                    @PathVariable("kieServerName") String kieServerName,
                                    @PathVariable(CONTAINER_ID) String id,
                                    String containerPayload) {

        return null;
    }


    @PutMapping(path = "/{kieServerName}/containers/{" + CONTAINER_ID + "}/status/activated",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response activateContainer(@RequestHeader HttpHeaders headers,
                                      @PathVariable("kieServerName") String kieServerName,
                                      @PathVariable(CONTAINER_ID) String id) {

        return null;
    }


    @PutMapping(path="/{kieServerName}/containers/{" + CONTAINER_ID + "}/status/deactivated",
            consumes={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deactivateContainer(@RequestHeader HttpHeaders headers,
                                        @PathVariable("kieServerName") String kieServerName,
                                        @PathVariable(CONTAINER_ID) String id) {

        return null;
    }


    @GetMapping(path="/{kieServerName}/containers/{" + CONTAINER_ID + "}",
            consumes={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getContainerInfo(@RequestHeader HttpHeaders headers,
                                     @PathVariable("kieServerName") String kieServerName,
                                     @PathVariable(CONTAINER_ID) String id) {
        return null;
    }


    @DeleteMapping(path="/{kieServerName}/containers/{" + CONTAINER_ID + "}",
            produces={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response disposeContainer(@RequestHeader HttpHeaders headers,
                                     @PathVariable("kieServerName") String kieServerName,
                                     @PathVariable(CONTAINER_ID) String id) {
        return null;
    }


    @GetMapping(path="/{kieServerName}/containers/{" + CONTAINER_ID + "}/scanner",
            produces={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getScannerInfo(@RequestHeader HttpHeaders headers,
                                   @PathVariable("kieServerName") String kieServerName,
                                   @PathVariable(CONTAINER_ID) String id) {
        return null;
    }


    @PostMapping(path="/{kieServerName}/containers/{" + CONTAINER_ID + "}/scanner",
            consumes={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateScanner(@RequestHeader HttpHeaders headers,
                                  @PathVariable("kieServerName") String kieServerName,
                                  @PathVariable(CONTAINER_ID) String id,
                                  String resourcePayload) {

        return null;
    }

    @GetMapping(path="/{kieServerName}/containers/{" + CONTAINER_ID + "}/release-id",
            produces={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getReleaseId(@RequestHeader HttpHeaders headers,
                                 @PathVariable(CONTAINER_ID) String id) {
        return null;
    }


    @PostMapping(path="/{kieServerName}/containers/{" + CONTAINER_ID + "}/release-id",
            consumes={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateReleaseId(@RequestHeader HttpHeaders headers,
                                    @PathVariable("kieServerName") String kieServerName,
                                    @PathVariable(CONTAINER_ID) String id,
                                    String releaseIdPayload,
                                    @RequestParam(value = "resetBeforeUpdate", defaultValue = "false") boolean resetBeforeUpdate) {

        return null;
    }

    @GetMapping(path="/{kieServerName}/state",consumes={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getServerState(@RequestHeader HttpHeaders headers) {
        return null;
    }


    @GetMapping(path="/{kieServerName}/readycheck",consumes={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response readycheck(@RequestHeader HttpHeaders headers,
                                    @PathVariable("kieServerName") String kieServerName) {
        return null;
    }

    @GetMapping(path="/{kieServerName}/healthcheck",consumes={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response healthcheck(@RequestHeader HttpHeaders headers,
                                @PathVariable("kieServerName") String kieServerName,
                                @RequestParam(value = "report", defaultValue = "false") boolean report) {
        return null;
    }

}
