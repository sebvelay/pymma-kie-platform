package org.chtijbug.kie.rest.backend;


import org.chtijbug.guvnor.server.jaxrs.api.UserLoginInformation;
import org.chtijbug.guvnor.server.jaxrs.jaxb.Asset;
import org.chtijbug.guvnor.server.jaxrs.jaxb.Package;
import org.chtijbug.guvnor.server.jaxrs.model.PlatformProjectResponse;
import org.chtijbug.kie.rest.backend.service.AssetService;
import org.guvnor.common.services.project.model.WorkspaceProject;
import org.guvnor.common.services.project.service.WorkspaceProjectService;
import org.guvnor.structure.organizationalunit.OrganizationalUnit;
import org.guvnor.structure.organizationalunit.OrganizationalUnitService;
import org.guvnor.structure.repositories.Branch;
import org.guvnor.structure.repositories.Repository;
import org.guvnor.structure.repositories.RepositoryService;
import org.kie.workbench.common.screens.datamodeller.service.DataModelerService;
import org.slf4j.LoggerFactory;
import org.uberfire.io.IOService;
import org.uberfire.java.nio.base.options.CommentedOption;
import org.uberfire.java.nio.file.DirectoryStream;
import org.uberfire.java.nio.file.Paths;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileAlreadyExistsException;
import java.util.*;

@Path("/chtijbug")
@Named
@ApplicationScoped
public class PackageResource {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PackageResource.class);

    @Context
    protected UriInfo uriInfo;

    @Context
    protected SecurityContext sc;

    @Inject
    @Named("ioStrategy")
    private IOService ioService;
    @Inject
    private OrganizationalUnitService organizationalUnitService;
    @Inject
    private RepositoryService repositoryService;
    @Inject
    private WorkspaceProjectService projectService;
    private RestTypeDefinition dotFileFilter = new RestTypeDefinition();
    @Inject
    private DataModelerService dataModelerService;
    @Inject
    private WorkspaceProjectService workspaceProjectService;
    @Inject
    private AssetService assetService;


    public PackageResource() {
        System.out.println("coucou");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public UserLoginInformation login() {

        UserLoginInformation userLoginInformation = new UserLoginInformation();

        userLoginInformation.setUsername(sc.getUserPrincipal().getName());
        for (String role : PermissionConstants.tableauChaine) {
            if (sc.isUserInRole(role) == true) {
                userLoginInformation.getRoles().add(role);
            }
        }
        userLoginInformation.setProjects(assetService.getAllProjects());
        return userLoginInformation;

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/detailedSpaces")
    // @RolesAllowed({REST_ROLE, REST_PROJECT_ROLE})
    public Collection<PlatformProjectResponse> getProjects() {
        logger.debug("-----getSpaces--- ");
        return assetService.getAllProjects();
    }

    @GET
    @Path("{organizationalUnitName}/{repositoryName}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Package> getPackagesAsJAXB(@PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("repositoryName") String repositoryName) {
        OrganizationalUnit organizationalUnit = organizationalUnitService.getOrganizationalUnit(organizationalUnitName);
        Collection<Repository> repositories = organizationalUnit.getRepositories();
        for (Repository repository : repositories) {
            if (repository.getAlias().equals(repositoryName)) {

                Optional<Branch> branch = repository.getDefaultBranch();
                Collection<WorkspaceProject> projects = projectService.getAllWorkspaceProjects(organizationalUnit);
                Collection<Package> packages = new ArrayList<>();
                for (WorkspaceProject project : projects) {
                    Package aPackage = new Package();
                    aPackage.setTitle(project.getName());
                    aPackage.setGroupID(project.getMainModule().getPom().getGav().getGroupId());
                    aPackage.setArtifactID(project.getMainModule().getPom().getGav().getArtifactId());
                    aPackage.setVersion(project.getMainModule().getPom().getGav().getVersion());

                    packages.add(aPackage);
                }
                return packages;
            }
        }
        return null;
    }

    @GET
    @Path("{organizationalUnitName}/{projectName}/assets")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Asset> getAssetsAsJAXB(
            @PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("projectName") String projectName) {
        try {
            List<Asset> contentList = new LinkedList<>();
            WorkspaceProject project = assetService.getProject(organizationalUnitName, projectName);
            if (project != null) {
                org.uberfire.backend.vfs.Path rootPath = project.getRootPath();
                org.uberfire.java.nio.file.Path nioPath = Paths.get(rootPath.toURI());
                DirectoryStream<org.uberfire.java.nio.file.Path> directoryStream = ioService.newDirectoryStream(nioPath);
                assetService.getContent(directoryStream, contentList);
            }


            return contentList;
        } catch (RuntimeException e) {
            throw new WebApplicationException(e);
        }
    }

    @GET
    @Path("{organizationalUnitName}/{projectName}/assets/{assetName}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Asset> getAssetAsJaxB(
            @PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("projectName") String projectName, @PathParam("assetName") String assetName) {
        List<Asset> resultList = new LinkedList<>();
        try {
            WorkspaceProject project = assetService.getProject(organizationalUnitName, projectName);
            if (project != null) {
                List<Asset> contentList = new LinkedList<>();
                org.uberfire.backend.vfs.Path rootPath = project.getRootPath();
                org.uberfire.java.nio.file.Path nioPath = Paths.get(rootPath.toURI());
                DirectoryStream<org.uberfire.java.nio.file.Path> directoryStream = ioService.newDirectoryStream(nioPath);
                assetService.getContent(directoryStream, contentList);
                for (Asset asset : contentList) {
                    if (asset.getTitle().equals(assetName)) {
                        resultList.add(asset);
                    }
                }
            }
            return resultList;
        } catch (RuntimeException e) {
            throw new WebApplicationException(e);
        }

    }

    @GET
    @Path("{organizationalUnitName}/{projectName}/assets/{assetName}/source")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getAssetSource(
            @PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("projectName") String projectName, @PathParam("assetName") String assetName) {
        List<Asset> resultList = new LinkedList<>();
        String result = "";
        try {
            WorkspaceProject project = assetService.getProject(organizationalUnitName, projectName);
            if (project != null) {
                org.uberfire.backend.vfs.Path rootPath = project.getRootPath();
                org.uberfire.java.nio.file.Path nioPath = Paths.get(rootPath.toURI());
                DirectoryStream<org.uberfire.java.nio.file.Path> directoryStream = ioService.newDirectoryStream(nioPath);
                result = assetService.getContentSource(directoryStream, assetName);
            }
            return result;
        } catch (RuntimeException e) {
            throw new WebApplicationException(e);
        }

    }

    @PUT
    @Path("{organizationalUnitName}/{projectName}/asset/{assetName}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateAssetFromJAXB(@Context HttpHeaders headers,
                                        @PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("projectName") String projectName,
                                        @PathParam("assetName") String assetName, String asset) {
        return updateAssetContent(headers, organizationalUnitName, projectName, assetName, asset, true);
    }

    @POST
    @Path("{organizationalUnitName}/{projectName}/newAsset")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Asset createAssetFromSourceAndJAXB(
            @PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("projectName") String projectName, Asset asset) {
        try {
            WorkspaceProject project = assetService.getProject(organizationalUnitName, projectName);
            if (project != null) {
                org.uberfire.backend.vfs.Path rootPath = project.getRootPath();
                org.uberfire.java.nio.file.Path nioPathDirectory = Paths.get(rootPath.toURI());
                DirectoryStream<org.uberfire.java.nio.file.Path> directoryStream = null;
                try {
                    directoryStream = ioService.newDirectoryStream(nioPathDirectory);
                    org.uberfire.java.nio.file.Path directoryWhereCreateAsset = assetService.getDirectoryElementPath(directoryStream, asset.getTitle());
                    if (directoryWhereCreateAsset != null) {
                        final org.uberfire.java.nio.file.Path nioPath = Paths.get(directoryWhereCreateAsset.toUri());
                        if (ioService.exists(nioPath)) {
                            throw new FileAlreadyExistsException(nioPath.toString());
                        }
                        CommentedOption commentedOption = new CommentedOption(asset.getComment());
                        ioService.write(nioPath, asset.getContent().getBytes(), commentedOption);
                    }
                } catch (Exception e) {

                } finally {
                    if (directoryStream != null) {
                        directoryStream.close();
                    }
                }


            }
        } catch (Exception e) {
            throw new WebApplicationException(e);
        }
        return null;
    }

    @POST
    @Path("{organizationalUnitName}/{projectName}/asset/{assetName}/source")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    public Response createAssetSource(@Context HttpHeaders headers,
                                      @PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("projectName") String projectName, @PathParam("assetName") String assetName, String content) {

        return updateAssetContent(headers, organizationalUnitName, projectName, assetName, content, true);
    }

    @PUT
    @Path("{organizationalUnitName}/{projectName}/asset/{assetName}/source")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    public Response updateAssetSource(@Context HttpHeaders headers,
                                      @PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("projectName") String projectName, @PathParam("assetName") String assetName, String content) {

        return updateAssetContent(headers, organizationalUnitName, projectName, assetName, content, false);
    }

    @DELETE
    @Path("{organizationalUnitName}/{projectName}/asset/{assetName}/source")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    public Response deleteAssetSource(@Context HttpHeaders headers,
                                      @PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("projectName") String projectName, @PathParam("assetName") String assetName, String content) {

        WorkspaceProject project = assetService.getProject(organizationalUnitName, projectName);

        if (project != null) {
            // Optional<Branch> rr = project.getRepository().getBranch("ee");
            // org.uberfire.backend.vfs.Path tata = rr.get().getPath();
            org.uberfire.backend.vfs.Path rootPath = project.getRootPath();
            org.uberfire.java.nio.file.Path nioPath = Paths.get(rootPath.toURI());

            DirectoryStream<org.uberfire.java.nio.file.Path> directoryStream = ioService.newDirectoryStream(nioPath);
            org.uberfire.java.nio.file.Path elementToDelete = assetService.getFileElementPath(directoryStream, assetName);
            if (elementToDelete == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            } else {

                File fileToUpdate = elementToDelete.toFile();
                if (fileToUpdate.isFile()) {
                    content = content.replace("\"", "");
                    ioService.delete(elementToDelete);
                    logger.debug("Returning OK response with content '{}'", content);
                    return Response.status(Response.Status.NO_CONTENT).build();
                } else {
                    return Response.status(Response.Status.NOT_MODIFIED).entity("Asset is not a file").build();
                }

            }
        } else {
            logger.info("Project {} or Organization {} not found ", projectName, organizationalUnitName);
            return Response.status(Response.Status.NOT_FOUND).entity("Project  or Organization  not found").build();
        }
    }


    private Response updateAssetContent(HttpHeaders headers, String organizationalUnitName, String projectName, String assetName, String content, boolean isCreate) {

        try {

            WorkspaceProject project = assetService.getProject(organizationalUnitName, projectName);

            if (project != null) {
                // Optional<Branch> rr = project.getRepository().getBranch("ee");
                // org.uberfire.backend.vfs.Path tata = rr.get().getPath();
                org.uberfire.backend.vfs.Path rootPath = project.getRootPath();
                org.uberfire.java.nio.file.Path nioPath = Paths.get(rootPath.toURI());

                DirectoryStream<org.uberfire.java.nio.file.Path> directoryStream = ioService.newDirectoryStream(nioPath);
                org.uberfire.java.nio.file.Path elementToUpdate = assetService.getFileElementPath(directoryStream, assetName);
                if (elementToUpdate != null && isCreate) {
                    return Response.status(Response.Status.CONFLICT).build();
                } else if (elementToUpdate != null && !isCreate) {
                    File fileToUpdate = elementToUpdate.toFile();
                    if (fileToUpdate.isFile()) {
                        content = content.replace("\"", "");
                        ioService.write(elementToUpdate, content);
                        logger.debug("Returning OK response with content '{}'", content);
                        return Response.status(Response.Status.ACCEPTED).build();
                    } else {
                        return Response.status(Response.Status.NOT_MODIFIED).entity("Asset is not a file").build();
                    }
                } else {//
                    if (isCreate) {
                        org.uberfire.java.nio.file.Path directoryWhereCreateAsset = assetService.getRuleDirectory(directoryStream, assetName);

                        if (directoryWhereCreateAsset != null) {
                            URI parentURI = directoryWhereCreateAsset.getParent().toUri();
                            URI uri = new URI(parentURI.getScheme(), parentURI.getUserInfo(), parentURI.getHost(), parentURI.getPort(), parentURI.getPath() + "/" + assetName, parentURI.getQuery(), parentURI.getFragment());
                            final org.uberfire.java.nio.file.Path nioPathWhere = Paths.get(uri);
                            CommentedOption commentedOption = new CommentedOption("Created from rest");
                            ioService.write(nioPathWhere, content.getBytes(), commentedOption);
                            return Response.status(Response.Status.CREATED).build();
                        } else {
                            return Response.status(Response.Status.NOT_FOUND).entity("no Rule package").build();
                        }
                    } else {
                        return Response.status(Response.Status.NOT_FOUND).entity("Asset not found").build();
                    }
                }
            } else {
                logger.info("Project {} or Organization {} not found ", projectName, organizationalUnitName);
                return Response.status(Response.Status.NOT_FOUND).entity("Project  or Organization  not found").build();
            }
        } catch (RuntimeException | URISyntaxException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }
    }

}
