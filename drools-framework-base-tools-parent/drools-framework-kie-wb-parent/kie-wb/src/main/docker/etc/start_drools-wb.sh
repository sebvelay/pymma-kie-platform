#!/usr/bin/env bash

# Start Wildfly with the given arguments.
echo "Running Drools Workbench on JBoss Wildfly..."
#export JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,address=50505,suspend=y,server=y"Je
exec ./standalone.sh -b  $JBOSS_BIND_ADDRESS -c $KIE_SERVER_PROFILE.xml  -Djava.net.preferIPv4Stack=true -Dorg.uberfire.metadata.index.dir=/home/lucene -Dorg.uberfire.nio.git.daemon.host=0.0.0.0 -Dorg.guvnor.m2repo.dir=/m2_kiewb/repository -DM2_HOME=/m2_kiewb/repository -Dorg.uberfire.nio.git.dir=/home/niodir -Dorg.uberfire.nio.git.dirname=gitBase -Dorg.appformer.m2repo.url=http://localhost:8080/kie-wb/maven2 -Dkie.maven.settings.custom=/m2_kiewb/settings.xml -Dfile.encoding=UTF-8 -Duser.language=fr -Duser.country=FR -Dorg.uberfire.ext.security.management.api.userManagementServices=KCCredentialsUserManagementService -Dorg.uberfire.ext.security.management.keycloak.authServer=http://keycloak:8080/auth -Dorg.uberfire.ext.security.management.keycloak.realm=demo -Dorg.uberfire.ext.security.management.keycloak.clientId=kie-git -Dorg.uberfire.ext.security.management.keycloak.clientSecret=a1c5a71b-0f14-4307-886d-f0e72e3d8a09 -Dorg.uberfire.ext.security.management.keycloak.user=admin -Dorg.uberfire.ext.security.management.keycloak.password=admin
exit $?



