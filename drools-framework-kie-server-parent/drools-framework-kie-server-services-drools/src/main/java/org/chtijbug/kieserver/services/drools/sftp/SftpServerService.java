package org.chtijbug.kieserver.services.drools.sftp;


import org.apache.sshd.common.NamedFactory;
import org.apache.sshd.common.PropertyResolverUtils;
import org.apache.sshd.common.file.virtualfs.VirtualFileSystemFactory;
import org.apache.sshd.server.Command;
import org.apache.sshd.server.ServerFactoryManager;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.scp.ScpCommandFactory;
import org.apache.sshd.server.subsystem.sftp.SftpSubsystemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

@Singleton
public class SftpServerService {

    private final String BANNER =
            "\n\nWelcome to Pymma Server kie server ssh Server!\n\n";
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private SshServer sshd;
    private String server = "0.0.0.0";
    private Integer port = -1;
    private String login = "kieserver";
    private String password = "kieserver1!";
    ;
    private String sftpDir = System.getProperty("org.chtijbug.server.tracedir");


    public SftpServerService() {

    }


    @PostConstruct
    public void initServer() throws IOException {
        if (System.getProperty("org.chtijbug.server.sftpPort") != null) {
            try {
                port = Integer.valueOf(System.getProperty("org.chtijbug.server.sftpPort")).intValue();
            } catch (NumberFormatException e) {
                port = 9080;
            }
        }
        if (System.getProperty("org.chtijbug.server.tracedir") != null) {
            sftpDir = System.getProperty("org.chtijbug.server.tracedir");
        }
        sshd = SshServer.setUpDefaultServer();
        PropertyResolverUtils.updateProperty(
                sshd,
                ServerFactoryManager.WELCOME_BANNER,
                BANNER);

        sshd.setHost(server);
        sshd.setPort(port);
        sshd.setPasswordAuthenticator(new MyPasswordAuthenticator(login, password));
        sshd.setPublickeyAuthenticator(new MyPublickeyAuthenticator());
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider());
        final String filePath = sftpDir;
        File f = new File(filePath);
        if (f.exists() == false) {
            f.mkdir();
        }
        sshd.setFileSystemFactory(new VirtualFileSystemFactory(Paths.get(filePath)));
        SftpSubsystemFactory factory = new SftpSubsystemFactory.Builder().build();
        sshd.setSubsystemFactories(Arrays.<NamedFactory<Command>>asList(factory));
        //sshd.setShellFactory(new ProcessShellFactory(new String[] { "/bin/sh", "-i", "-l" }));
        sshd.setCommandFactory(new ScpCommandFactory());
        // sshd.setShellFactory( new SshSessionFactory() );
        sshd.start();
        logger.info("Serveur SSH demarre sur host " + this.server + " port " + this.port);
    }


    public void setServer(String server) {
    }
}