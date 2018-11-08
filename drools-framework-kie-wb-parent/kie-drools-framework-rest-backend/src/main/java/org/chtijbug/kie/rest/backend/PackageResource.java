package org.chtijbug.kie.rest.backend;


import org.guvnor.common.services.project.model.WorkspaceProject;
import org.guvnor.common.services.project.service.WorkspaceProjectService;
import org.guvnor.rest.client.ProjectResponse;
import org.guvnor.structure.organizationalunit.OrganizationalUnit;
import org.guvnor.structure.organizationalunit.OrganizationalUnitService;
import org.guvnor.structure.repositories.PublicURI;
import org.guvnor.structure.repositories.Repository;
import org.guvnor.structure.repositories.RepositoryService;
import org.slf4j.LoggerFactory;
import org.uberfire.io.IOService;
import org.uberfire.java.nio.file.DirectoryStream;
import org.uberfire.spaces.Space;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


@Path("/chtijbug")
@Named
@ApplicationScoped
public class PackageResource {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PackageResource.class);

    @Context
    protected UriInfo uriInfo;

    @Inject
    @Named("ioStrategy")
    private IOService ioService;

    @Inject
    private OrganizationalUnitService organizationalUnitService;

    @Inject
    private RepositoryService repositoryService;


    @Inject
    private WorkspaceProjectService workspaceProjectService;

    public PackageResource() {
        System.out.println("coucou");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/detailedSpaces")
    // @RolesAllowed({REST_ROLE, REST_PROJECT_ROLE})
    public Collection<ProjectResponse> getProjects() {
        logger.debug("-----getSpaces--- ");

        final List<ProjectResponse> spaces = new ArrayList<>();
        for (OrganizationalUnit ou : organizationalUnitService.getOrganizationalUnits()) {
            spaces.addAll(getSpace(ou));
        }

        return spaces;
    }

    private List<ProjectResponse> getSpace(OrganizationalUnit ou) {
        final Space space = new Space(ou.getName());

        final List<ProjectResponse> repoNames = new ArrayList<>();
        for (WorkspaceProject workspaceProject : workspaceProjectService.getAllWorkspaceProjects(ou)) {
            repoNames.add(getProjectResponse(workspaceProject));
        }

        return repoNames;
    }

    private ProjectResponse getProjectResponse(WorkspaceProject workspaceProject) {
        final ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setName(workspaceProject.getName());
        projectResponse.setSpaceName(workspaceProject.getOrganizationalUnit().getName());

        if (workspaceProject.getMainModule() != null) {
            projectResponse.setGroupId(workspaceProject.getMainModule().getPom().getGav().getGroupId());
            projectResponse.setVersion(workspaceProject.getMainModule().getPom().getGav().getVersion());
            projectResponse.setDescription(workspaceProject.getMainModule().getPom().getDescription());
        }

        final ArrayList<org.guvnor.rest.client.PublicURI> publicURIs = new ArrayList<>();

        for (PublicURI publicURI : workspaceProject.getRepository().getPublicURIs()) {
            final org.guvnor.rest.client.PublicURI responseURI = new org.guvnor.rest.client.PublicURI();
            responseURI.setProtocol(publicURI.getProtocol());
            responseURI.setUri(publicURI.getURI());
            publicURIs.add(responseURI);
        }

        projectResponse.setPublicURIs(publicURIs);
        return projectResponse;
    }

    @GET
    @Path("{organizationalUnitName}/{repositoryName}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Package> getPackagesAsJAXB(@PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("repositoryName") String repositoryName) {
        OrganizationalUnit organizationalUnit = organizationalUnitService.getOrganizationalUnit(organizationalUnitName);
        Collection<Repository> repositories = organizationalUnit.getRepositories();
        for (Repository repository : repositories) {
            if (repository.getAlias().equals(repositoryName)) {


                Collection<Package> packages = new ArrayList<>();

                return packages;
            }
        }
        return null;
    }

    @GET
    @Path("{organizationalUnitName}/{repositoryName}/{packageName}/assets")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<String> getAssetsAsJAXB(
            @PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("repositoryName") String repositoryName,
            @PathParam("packageName") String packageName,
            @QueryParam("format") List<String> formats) {
        try {
            List<String> contentList = new LinkedList<>();


            return contentList;
        } catch (RuntimeException e) {
            throw new WebApplicationException(e);
        }
    }

    private String getProject(String organizationalUnitName, String repositoryName, String packageName) {
        OrganizationalUnit organizationalUnit = organizationalUnitService.getOrganizationalUnit(organizationalUnitName);
        Collection<Repository> repositories = organizationalUnit.getRepositories();

        return "tto";
    }


    private void getContentSource(DirectoryStream<org.uberfire.java.nio.file.Path> directoryStream, String asset, List<org.uberfire.java.nio.file.Path> pathLinkedList) {
        for (org.uberfire.java.nio.file.Path elementPath : directoryStream) {
            if (org.uberfire.java.nio.file.Files.isDirectory(elementPath)) {
                DirectoryStream<org.uberfire.java.nio.file.Path> adirectoryStream = ioService.newDirectoryStream(elementPath);
                getContentSource(adirectoryStream, asset, pathLinkedList);
            } else {

            }
        }
    }

    private void getContent(DirectoryStream<org.uberfire.java.nio.file.Path> directoryStream, Collection<String> contentList) {
        for (org.uberfire.java.nio.file.Path elementPath : directoryStream) {
            if (org.uberfire.java.nio.file.Files.isDirectory(elementPath)) {
                DirectoryStream<org.uberfire.java.nio.file.Path> adirectoryStream = ioService.newDirectoryStream(elementPath);
                getContent(adirectoryStream, contentList);
            } else {

            }
        }
    }

    private org.uberfire.java.nio.file.Path getFileElementPath(DirectoryStream<org.uberfire.java.nio.file.Path> directoryStream, String assetName) {
        for (org.uberfire.java.nio.file.Path elementPath : directoryStream) {
            if (org.uberfire.java.nio.file.Files.isDirectory(elementPath)) {
                DirectoryStream<org.uberfire.java.nio.file.Path> adirectoryStream = ioService.newDirectoryStream(elementPath);
                org.uberfire.java.nio.file.Path foundElementPath = getFileElementPath(adirectoryStream, assetName);
                if (foundElementPath != null) {
                    return foundElementPath;
                }
            } else {

            }
        }
        return null;
    }

    private org.uberfire.java.nio.file.Path getDirectoryElementPath(DirectoryStream<org.uberfire.java.nio.file.Path> directoryStream, String assetName) {
        for (org.uberfire.java.nio.file.Path elementPath : directoryStream) {
            if (org.uberfire.java.nio.file.Files.isDirectory(elementPath)) {
                DirectoryStream<org.uberfire.java.nio.file.Path> adirectoryStream = ioService.newDirectoryStream(elementPath);
                if (elementPath.getFileName().toString().equals(assetName)) {
                    return elementPath;
                }
                org.uberfire.java.nio.file.Path foundElementPath = getDirectoryElementPath(adirectoryStream, assetName);
                if (foundElementPath != null) {
                    return foundElementPath;
                }
            }
        }
        return null;
    }

    @GET
    @Path("{organizationalUnitName}/{repositoryName}/{packageName}/assets/{assetName}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<String> getAssetAsJaxB(
            @PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("repositoryName") String repositoryName,
            @PathParam("packageName") String packageName, @PathParam("assetName") String assetName) {
        List<String> resultList = new LinkedList<>();
        try {

            return resultList;
        } catch (RuntimeException e) {
            throw new WebApplicationException(e);
        }

    }

    @GET
    @Path("{organizationalUnitName}/{repositoryName}/{packageName}/assets/{assetName}/source")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getAssetSource(
            @PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("repositoryName") String repositoryName,
            @PathParam("packageName") String packageName, @PathParam("assetName") String assetName) {
        List<String> resultList = new LinkedList<>();
        String result = "";
        try {

            return result;
        } catch (RuntimeException e) {
            throw new WebApplicationException(e);
        }

    }

    @PUT
    @Path("{organizationalUnitName}/{repositoryName}/{packageName}/asset/{assetName}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void updateAssetFromJAXB(
            @PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("repositoryName") String repositoryName,
            @PathParam("packageName") String packageName,
            @PathParam("assetName") String assetName, String asset) {
        try {

        } catch (RuntimeException e) {
            throw new WebApplicationException(e);
        }
    }

    @POST
    @Path("{organizationalUnitName}/{repositoryName}/{packageName}/newAsset")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String createAssetFromSourceAndJAXB(
            @PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("repositoryName") String repositoryName,
            @PathParam("packageName") String packageName, String asset) {
        try {

        } catch (Exception e) {
            throw new WebApplicationException(e);
        }
        return null;
    }

    @PUT
    @Path("{organizationalUnitName}/{repositoryName}/{packageName}/asset/{assetName}/source")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    @Produces({MediaType.WILDCARD})
    public void updateAssetSource(
            @PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("repositoryName") String repositoryName,
            @PathParam("packageName") String packageName, @PathParam("assetName") String assetName, String content) {
        try {


        } catch (Exception e) {
            throw new WebApplicationException(e);
        }

    }

}
