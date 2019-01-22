package org.chtijbug.kie.rest.backend;


import org.chtijbug.guvnor.server.jaxrs.api.UserLoginInformation;
import org.chtijbug.guvnor.server.jaxrs.jaxb.Asset;
import org.chtijbug.guvnor.server.jaxrs.jaxb.Package;
import org.chtijbug.guvnor.server.jaxrs.model.PlatformProjectResponse;
import org.guvnor.common.services.project.model.Module;
import org.guvnor.common.services.project.model.WorkspaceProject;
import org.guvnor.common.services.project.service.WorkspaceProjectService;
import org.guvnor.structure.organizationalunit.OrganizationalUnit;
import org.guvnor.structure.organizationalunit.OrganizationalUnitService;
import org.guvnor.structure.repositories.Branch;
import org.guvnor.structure.repositories.PublicURI;
import org.guvnor.structure.repositories.Repository;
import org.guvnor.structure.repositories.RepositoryService;
import org.kie.workbench.common.screens.datamodeller.model.EditorModelContent;
import org.kie.workbench.common.screens.datamodeller.service.DataModelerService;
import org.kie.workbench.common.services.datamodeller.core.DataObject;
import org.kie.workbench.common.services.datamodeller.core.impl.DataModelImpl;
import org.kie.workbench.common.services.shared.project.KieModule;
import org.slf4j.LoggerFactory;
import org.uberfire.io.IOService;
import org.uberfire.java.nio.base.options.CommentedOption;
import org.uberfire.java.nio.file.DirectoryStream;
import org.uberfire.java.nio.file.Paths;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
        userLoginInformation.setProjects(getAllProjects());
        return userLoginInformation;

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/detailedSpaces")
    // @RolesAllowed({REST_ROLE, REST_PROJECT_ROLE})
    public Collection<PlatformProjectResponse> getProjects() {
        logger.debug("-----getSpaces--- ");


        return getAllProjects();
    }

    private List<PlatformProjectResponse> getAllProjects() {
        final List<PlatformProjectResponse> spaces = new ArrayList<>();
        for (OrganizationalUnit ou : organizationalUnitService.getOrganizationalUnits()) {
            spaces.addAll(getSpace(ou));
        }

        return spaces;
    }

    private List<PlatformProjectResponse> getSpace(OrganizationalUnit ou) {

        final List<PlatformProjectResponse> repoNames = new ArrayList<>();
        for (WorkspaceProject workspaceProject : workspaceProjectService.getAllWorkspaceProjects(ou)) {
            repoNames.add(getProjectResponse(workspaceProject));
        }

        return repoNames;
    }
    private void toto(){

    }


    private PlatformProjectResponse getProjectResponse(WorkspaceProject workspaceProject) {
        final PlatformProjectResponse projectResponse = new PlatformProjectResponse();
        projectResponse.setName(workspaceProject.getName());
        projectResponse.setSpaceName(workspaceProject.getOrganizationalUnit().getName());

        if (workspaceProject.getMainModule() != null) {
            Module kmodule = workspaceProject.getMainModule();
            EditorModelContent econtent = dataModelerService.loadContent(((KieModule) kmodule).getImportsPath());
            DataModelImpl econtentDataModel = (DataModelImpl)econtent.getDataModel();
            List<DataObject> dataObjects = econtentDataModel.getExternalClasses();
            for (DataObject dataObject : dataObjects){
                System.out.println(dataObject.toString());
                String className="class="+dataObject.getPackageName()+"."+dataObject.getName();
                projectResponse.getJavaClasses().add(className);

              }
            projectResponse.setArtifactId(workspaceProject.getMainModule().getPom().getGav().getArtifactId());
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
            WorkspaceProject project = getProject(organizationalUnitName, projectName);
            if (project != null) {
                org.uberfire.backend.vfs.Path rootPath = project.getRootPath();
                org.uberfire.java.nio.file.Path nioPath = Paths.get(rootPath.toURI());
                DirectoryStream<org.uberfire.java.nio.file.Path> directoryStream = ioService.newDirectoryStream(nioPath);
                getContent(directoryStream, contentList);
            }


            return contentList;
        } catch (RuntimeException e) {
            throw new WebApplicationException(e);
        }
    }

    private WorkspaceProject getProject(String organizationalUnitName, String projectName) {
        OrganizationalUnit organizationalUnit = organizationalUnitService.getOrganizationalUnit(organizationalUnitName);
        Collection<WorkspaceProject> workspaceProjects = projectService.getAllWorkspaceProjects(organizationalUnit);

        for (WorkspaceProject project : workspaceProjects) {
            if (project.getName().equals(projectName)) {
                return project;
            }
        }
        // }
        //}
        return null;
    }


    private void getContentSource(DirectoryStream<org.uberfire.java.nio.file.Path> directoryStream, Asset asset, List<org.uberfire.java.nio.file.Path> pathLinkedList) {
        for (org.uberfire.java.nio.file.Path elementPath : directoryStream) {
            if (org.uberfire.java.nio.file.Files.isDirectory(elementPath)) {
                DirectoryStream<org.uberfire.java.nio.file.Path> adirectoryStream = ioService.newDirectoryStream(elementPath);
                getContentSource(adirectoryStream, asset, pathLinkedList);
            } else {
                if (dotFileFilter.accept(elementPath.getFileName().toString())) {
                    Map<String, Object> listAttributes = ioService.readAttributes(elementPath);
                    if (asset.getTitle().equals(elementPath.getFileName().toString())) {
                        pathLinkedList.add(elementPath);
                    }
                }
            }
        }
    }

    private void getContent(DirectoryStream<org.uberfire.java.nio.file.Path> directoryStream, Collection<Asset> contentList) {
        for (org.uberfire.java.nio.file.Path elementPath : directoryStream) {
            if (org.uberfire.java.nio.file.Files.isDirectory(elementPath)) {
                DirectoryStream<org.uberfire.java.nio.file.Path> adirectoryStream = ioService.newDirectoryStream(elementPath);
                getContent(adirectoryStream, contentList);
            } else {
                if (dotFileFilter.accept(elementPath.getFileName().toString())) {
                    Map<String, Object> listAttributes = ioService.readAttributes(elementPath);
                    Asset asset = new Asset();
                    asset.setTitle(elementPath.getFileName().toString());
                    asset.setDirectory(elementPath.getParent().toString());
                    asset.setRefLink(elementPath.getFileName().toUri());
                    contentList.add(asset);
                }
            }
        }
    }

    private String getContentSource(DirectoryStream<org.uberfire.java.nio.file.Path> directoryStream, String assetName) {
        for (org.uberfire.java.nio.file.Path elementPath : directoryStream) {
            if (org.uberfire.java.nio.file.Files.isDirectory(elementPath)) {
                DirectoryStream<org.uberfire.java.nio.file.Path> adirectoryStream = ioService.newDirectoryStream(elementPath);
                String result = getContentSource(adirectoryStream, assetName);
                if (result != null && result.length() > 0) {
                    return result;
                }
            } else {

                if (elementPath.getFileName().toString().startsWith(".") == false) {
                    if (elementPath.getFileName().toString().equals(assetName)) {
                        return ioService.readAllString(elementPath);
                    }
                }
            }
        }
        return null;
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
                if (dotFileFilter.accept(elementPath.getFileName().toString())) {
                    return elementPath;
                }
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
    @Path("{organizationalUnitName}/{projectName}/assets/{assetName}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Asset> getAssetAsJaxB(
            @PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("projectName") String projectName, @PathParam("assetName") String assetName) {
        List<Asset> resultList = new LinkedList<>();
        try {
            WorkspaceProject project = getProject(organizationalUnitName, projectName);
            if (project != null) {
                List<Asset> contentList = new LinkedList<>();
                org.uberfire.backend.vfs.Path rootPath = project.getRootPath();
                org.uberfire.java.nio.file.Path nioPath = Paths.get(rootPath.toURI());
                DirectoryStream<org.uberfire.java.nio.file.Path> directoryStream = ioService.newDirectoryStream(nioPath);
                getContent(directoryStream, contentList);
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
            WorkspaceProject project = getProject(organizationalUnitName, projectName);
            if (project != null) {
                org.uberfire.backend.vfs.Path rootPath = project.getRootPath();
                org.uberfire.java.nio.file.Path nioPath = Paths.get(rootPath.toURI());
                DirectoryStream<org.uberfire.java.nio.file.Path> directoryStream = ioService.newDirectoryStream(nioPath);
                result = getContentSource(directoryStream, assetName);
            }
            return result;
        } catch (RuntimeException e) {
            throw new WebApplicationException(e);
        }

    }

    @PUT
    @Path("{organizationalUnitName}/{projectName}/asset/{assetName}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void updateAssetFromJAXB(
            @PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("projectName") String projectName,
            @PathParam("assetName") String assetName, String asset) {
        updateAssetContent(organizationalUnitName, projectName, assetName, asset);
    }

    @POST
    @Path("{organizationalUnitName}/{projectName}/newAsset")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Asset createAssetFromSourceAndJAXB(
            @PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("projectName") String projectName, Asset asset) {
        try {
            WorkspaceProject project = getProject(organizationalUnitName, projectName);
            org.uberfire.backend.vfs.Path rootPath = project.getRootPath();
            org.uberfire.java.nio.file.Path nioPathDirectory = Paths.get(rootPath.toURI());
            DirectoryStream<org.uberfire.java.nio.file.Path> directoryStream = ioService.newDirectoryStream(nioPathDirectory);

            org.uberfire.java.nio.file.Path directoryWhereCreateAsset = getDirectoryElementPath(directoryStream, asset.getTitle());
            final org.uberfire.java.nio.file.Path nioPath = Paths.get(directoryWhereCreateAsset.toUri());
            if (ioService.exists(nioPath)) {
                throw new FileAlreadyExistsException(nioPath.toString());
            }
            CommentedOption commentedOption = new CommentedOption(asset.getComment());
            ioService.write(nioPath, asset.getContent().getBytes(), commentedOption);
        } catch (Exception e) {
            throw new WebApplicationException(e);
        }
        return null;
    }

    @PUT
    @Path("{organizationalUnitName}/{projectName}/asset/{assetName}/source")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    @Produces({MediaType.WILDCARD})
    public void updateAssetSource(
            @PathParam("organizationalUnitName") String organizationalUnitName, @PathParam("projectName") String projectName, @PathParam("assetName") String assetName, String content) {
        updateAssetContent(organizationalUnitName, projectName, assetName, content);

    }

    private void updateAssetContent(String organizationalUnitName, String projectName, String assetName, String content) {
        try {
            WorkspaceProject project = getProject(organizationalUnitName, projectName);

            if (project != null) {
                org.uberfire.backend.vfs.Path rootPath = project.getRootPath();
                org.uberfire.java.nio.file.Path nioPath = Paths.get(rootPath.toURI());
                DirectoryStream<org.uberfire.java.nio.file.Path> directoryStream = ioService.newDirectoryStream(nioPath);
                org.uberfire.java.nio.file.Path elementToUpdate = getFileElementPath(directoryStream, assetName);
                File fileToUpdate = elementToUpdate.toFile();
                if (fileToUpdate.isFile()) {
                    FileOutputStream fileOutputStream = new FileOutputStream(fileToUpdate);
                    fileOutputStream.write(content.getBytes());
                    fileOutputStream.close();
                }

            }
        } catch (RuntimeException e) {
            throw new WebApplicationException(e);
        } catch (FileNotFoundException e) {
            throw new WebApplicationException(e);
        } catch (IOException e) {
            throw new WebApplicationException(e);
        }
    }

}
