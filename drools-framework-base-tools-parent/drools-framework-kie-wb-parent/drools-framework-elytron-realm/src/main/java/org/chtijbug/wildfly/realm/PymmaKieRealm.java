/*
 * Copyright 2018 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.chtijbug.wildfly.realm;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.wildfly.extension.elytron.Configurable;
import org.wildfly.security.auth.SupportLevel;
import org.wildfly.security.auth.server.RealmIdentity;
import org.wildfly.security.auth.server.RealmUnavailableException;
import org.wildfly.security.auth.server.SecurityRealm;
import org.wildfly.security.credential.Credential;
import org.wildfly.security.evidence.Evidence;
import org.wildfly.security.evidence.PasswordGuessEvidence;

import java.security.Principal;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.Map;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * Example of custom-realm for WildFly Elytron
 * Realm providing one identity "myadmin" with password "mypassword"
 *
 * @author <a href="mailto:jkalina@redhat.com">Jan Kalina</a>
 */
public class PymmaKieRealm implements SecurityRealm, Configurable {

    private String connectionString;
    private String databaseName;


    private MongoClient mongoClient;
    CodecRegistry pojoCodecRegistry;
    MongoDatabase database;
    // receiving configuration from subsystem
    public void initialize(Map<String, String> configuration) {
        connectionString = configuration.get("connectionString");
        databaseName = configuration.get("name");

        System.out.println("PymmaKieRealm initialized with databaseName = " + connectionString );


        mongoClient = MongoClients.create(connectionString);
        pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        database = mongoClient.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);
        System.out.println("All setup");
    }

    // this realm does not allow acquiring credentials
    public SupportLevel getCredentialAcquireSupport(Class<? extends Credential> credentialType, String algorithmName,
            AlgorithmParameterSpec parameterSpec) throws RealmUnavailableException {
        return SupportLevel.UNSUPPORTED;
    }

    // this realm will be able to verify password evidences only
    public SupportLevel getEvidenceVerifySupport(Class<? extends Evidence> evidenceType, String algorithmName)
            throws RealmUnavailableException {
        return PasswordGuessEvidence.class.isAssignableFrom(evidenceType) ? SupportLevel.POSSIBLY_SUPPORTED : SupportLevel.UNSUPPORTED;
    }

    public RealmIdentity getRealmIdentity(final Principal principal) throws RealmUnavailableException {

        if ("myadmin".equals(principal.getName())) { // identity "myadmin" will have password "mypassword"
            return new RealmIdentity() {
                public Principal getRealmIdentityPrincipal() {
                    return principal;
                }

                public SupportLevel getCredentialAcquireSupport(Class<? extends Credential> credentialType,
                        String algorithmName, AlgorithmParameterSpec parameterSpec) throws RealmUnavailableException {
                    return SupportLevel.UNSUPPORTED;
                }

                public <C extends Credential> C getCredential(Class<C> credentialType) throws RealmUnavailableException {
                    return null;
                }

                public SupportLevel getEvidenceVerifySupport(Class<? extends Evidence> evidenceType, String algorithmName)
                        throws RealmUnavailableException {
                    return PasswordGuessEvidence.class.isAssignableFrom(evidenceType) ? SupportLevel.SUPPORTED : SupportLevel.UNSUPPORTED;
                }

                // evidence will be accepted if it is password "mypassword"
                public boolean verifyEvidence(Evidence evidence) throws RealmUnavailableException {
                    if (evidence instanceof PasswordGuessEvidence) {
                        PasswordGuessEvidence guess = (PasswordGuessEvidence) evidence;
                        try {
                            return Arrays.equals("mypassword".toCharArray(), guess.getGuess());

                        } finally {
                            guess.destroy();
                        }
                    }
                    return false;
                }

                public boolean exists() throws RealmUnavailableException {
                    return true;
                }
            };
        }

        return RealmIdentity.NON_EXISTENT;
    }

}
