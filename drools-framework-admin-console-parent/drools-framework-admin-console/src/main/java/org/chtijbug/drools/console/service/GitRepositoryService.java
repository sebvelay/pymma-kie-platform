package org.chtijbug.drools.console.service;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@DependsOn("applicationContext")
public class GitRepositoryService {

    private static Logger logger = LoggerFactory.getLogger(GitRepositoryService.class);

    public static void main(String[] args) {
        GitRepositoryService service = new GitRepositoryService();
        service.executeCommand("cd /Users/nheron/kie-base-jbpm-6/niodir/.niogit/nico1.git && ./hooks/post-commit ");
    }

    public void addHookToRepo(String projectName, String gitRepoBase,String gitBaseUrl) throws IOException {
        String fileBase = System.getProperty("org.uberfire.nio.git.dir");
        if (fileBase != null) {
            if (gitRepoBase==null || gitBaseUrl.length()==0){
                gitRepoBase=".niogit";
            }
            String gitdirectory = fileBase + "/"+gitRepoBase+"/" + projectName + ".git";
            String gitFileName = fileBase + "/"+gitRepoBase+"/" + projectName + ".git/hooks/post-commit";
            /**
             * #!/bin/sh
             * REPO=git@gitlab.pymma-software.com:nheron/jbpm-edop-base-example.git
             * git push --mirror $REPO;
             * echo "====push done ===="
             */
            String gitRepo = gitBaseUrl + "/" + projectName + ".git";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("#!/bin/sh").append("\n");
            stringBuilder.append("currentDir=$PWD").append("\n");
            stringBuilder.append("cd ").append(gitdirectory).append("\n");
            stringBuilder.append("REPO=").append(gitRepo).append("\n");
            stringBuilder.append("git push --mirror $REPO;").append("\n");
            stringBuilder.append("echo \"====push done ====\"  ").append("\n");
            stringBuilder.append("cd $currentDir  ").append("\n");
            File file = new File(gitFileName);
            FileUtils.writeStringToFile(file, stringBuilder.toString());
            boolean executable = file.setExecutable(true, true);
            logger.info("executable {}",executable);
        }
    }

    public void executeHookPush(String projectName, String gitRepoBase,String gitBaseUrl) {
        String fileBase = System.getProperty("org.uberfire.nio.git.dir");
        if (fileBase != null) {
            if (gitRepoBase==null || gitBaseUrl.length()==0){
                gitRepoBase=".niogit";
            }
            String gitFileName = fileBase + "/"+gitRepoBase+"/" + projectName + ".git/hooks/post-commit";
            this.executeCommand(gitFileName);
        }
    }

    private String executeCommand(String command) {

        StringBuilder output = new StringBuilder();

        Process process;
        try {
            logger.info(command);
            logger.info("============Command start======================");
            process = Runtime.getRuntime().exec(command);
            process.waitFor();
            logger.info("=============Command Stop=====================");
            new Thread(() -> {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line = "";
                    try {
                        while ((line = reader.readLine()) != null)
                            logger.info(line);
                    } finally {
                        reader.close();
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }).start();

// Consommation de la sortie d'erreur de l'application externe dans un Thread separe
            new Thread(() -> {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    String line = "";
                    try {
                        while ((line = reader.readLine()) != null) {
                            logger.info(line);
                            // Traitement du flux d'erreur de l'application si besoin est
                        }
                    } finally {
                        reader.close();
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }


}
