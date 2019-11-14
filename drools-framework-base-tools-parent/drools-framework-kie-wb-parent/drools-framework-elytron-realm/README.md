Pymma kie realm for WildFly Elytron
=======================================

Simple demonstration security realm for WildFly Elytron providing one user identity "myadmin" with password "mypassword".
It support password verification only, so it can be used with plain-text authentication mechanisms like BASIC, PLAIN or FORM.
To support mechanisms like DIGEST or SCRAM you will need to implement credential acquirement too.

Usage
*****

Compile:

        mvn package

Add the module into the WildFly:

        bin/jboss-cli.sh
        module add --name=com.pymmasoftware.kie-realm --resources=custom-realm-1.0.0.Alpha1-SNAPSHOT.jar --dependencies=org.wildfly.security.elytron,org.wildfly.extension.elytron

Add a custom-realm into the subsystem:

        /subsystem=elytron/custom-realm=pymmaKieRealm:add(module=com.pymmasoftware.kie-realm, class-name=org.chtijbug.wildfly.realm.PymmaKieRealm, configuration={myAttribute="myValue"})


use it
/subsystem=elytron/security-domain=ApplicationDomain:list-add(name=realms, index=0, value={realm=pymmaKieRealm})
/subsystem=elytron/security-domain=ApplicationDomain:write-attribute(name=default-realm, value=pymmaKieRealm)
reload