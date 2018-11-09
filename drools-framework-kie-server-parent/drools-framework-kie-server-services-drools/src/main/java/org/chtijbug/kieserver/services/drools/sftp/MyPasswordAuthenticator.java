package org.chtijbug.kieserver.services.drools.sftp;


import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.session.ServerSession;

/**
 * Very basic PasswordAuthenticator used for unit tests.
 */
public class MyPasswordAuthenticator implements PasswordAuthenticator {
    private String login;
    private String password;

    public MyPasswordAuthenticator(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public boolean authenticate(String username, String password, ServerSession session) {
        boolean retour = false;

        if (this.login != null
                && this.password != null
                && this.login.equals(username)
                && this.password.equals(password)) {
            retour = true;
        }
        return retour;
    }
}
