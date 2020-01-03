package org.chtijbug.drools.console.service;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.View;
import org.apache.commons.io.IOUtils;
import org.chtijbug.drools.console.service.model.gitlab.GitLabConfigurationData;
import org.chtijbug.drools.console.service.model.jenkins.JenkinsConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@Service
@DependsOn("applicationContext")
public class JenkinsService {
    JenkinsServer jenkins = null;

    public void createJobForRepo(String jenkinsServerUrl, String username, String password, String viewName, String groupId, String artifactId, String pomXml, String nexusName, String nexusUrl, String jdkVersion) throws URISyntaxException, IOException {
        GitLabConfigurationData configGitLab = AppContext.getApplicationContext().getBean(GitLabConfigurationData.class);
        JenkinsConfigurationData jenkinsConfigurationData = AppContext.getApplicationContext().getBean(JenkinsConfigurationData.class);

        if (jenkins == null) {
            jenkins = new JenkinsServer(new URI(jenkinsServerUrl), username, password);

        }

        Map<String, View> views = jenkins.getViews();

        if (views.containsKey(jenkinsConfigurationData.getGroupName())) {
            View view = views.get(viewName);
            InputStream stream = new ClassPathResource("/META-INF/DefaultJbpmArtefactJenkinsBuildProject.xml").getInputStream();
            String fileContent = IOUtils.toString(stream);
            fileContent = fileContent.replace(":gitRepoUrl", configGitLab.getBaseUrl() + "/" + artifactId + ".git");
            fileContent = fileContent.replace(":gitCredentials", jenkinsConfigurationData.getCredentials());
            fileContent = fileContent.replace(":groupId", groupId);
            fileContent = fileContent.replace(":artifactid", artifactId);
            fileContent = fileContent.replace(":pomxml", pomXml);
            fileContent = fileContent.replace(":nexusName", nexusName);
            fileContent = fileContent.replace(":nexusUrl", nexusUrl);
            fileContent = fileContent.replace(":jdkVersion", jdkVersion);

            String newJobName = groupId.replace(".", "dot") + "dot" + artifactId;
            jenkins.createJob(newJobName, fileContent, false);
            boolean jobNotYetCreated = true;
            while (jobNotYetCreated) {
                JobWithDetails jobWithDetails = jenkins.getJob(newJobName);
                if (jobWithDetails != null) {
                    jobNotYetCreated = false;
                } else {
                    synchronized (this) {
                        try {
                            Thread.currentThread().wait(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }


            InputStream streamView = new ClassPathResource("/META-INF/DefaultViewConfig.xml").getInputStream();
            String fileContentView = IOUtils.toString(streamView);

            StringBuilder stringBuilder = new StringBuilder();
            for (Job job : view.getJobs()) {
                stringBuilder.append("<string>").append(job.getName()).append("</string>").append("\n");
            }
            stringBuilder.append("<string>").append(newJobName).append("</string>").append("\n");
            fileContentView = fileContentView.replace(":listJobs", stringBuilder.toString());
            jenkins.updateView(view.getName(), fileContentView);
        }

    }
}
