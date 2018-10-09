package org.chtijbug.drools.console.service;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class GitRepositoryService {

    public static void main(String[] args) {
        GitRepositoryService service = new GitRepositoryService();
        service.executeCommand("cd /Users/nheron/kie-base-jbpm-6/niodir/.niogit/nico1.git && ./hooks/post-commit ");
    }

    public void addHookToRepo(String projectName, String gitBaseUrl) throws IOException {
        String fileBase = System.getProperty("org.uberfire.nio.git.dir");
        if (fileBase != null) {
            String gitdirectory = fileBase + "/.niogit/" + projectName + ".git";
            String gitFileName = fileBase + "/.niogit/" + projectName + ".git/hooks/post-commit";
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
            file.setExecutable(true, true);
        }
    }

    public void executeHookPush(String projectName, String gitBaseUrl) {
        String fileBase = System.getProperty("org.uberfire.nio.git.dir");
        if (fileBase != null) {

            String gitFileName = fileBase + "/.niogit/" + projectName + ".git/hooks/post-commit";
            this.executeCommand(gitFileName);
        }
    }

    private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process process;
        try {
            System.out.println(command);
            System.out.println("============Command start======================");
            process = Runtime.getRuntime().exec(command);
            process.waitFor();
            System.out.println("=============Command Stop=====================");
            new Thread(() -> {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line = "";
                    try {
                        while ((line = reader.readLine()) != null)
                            System.out.println(line);
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
                            System.out.println(line);
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
