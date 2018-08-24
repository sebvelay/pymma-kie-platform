

-XX:MaxNewSize=256m
-XX:PermSize=256m
-XX:MaxPermSize=556m
-XX:+DisableExplicitGC
-Dhibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
-Dorg.kie.example.repositories=/Users/nheron/kie-base-jbpm-710/example-import
-Dorg.kie.example=true
-Dorg.uberfire.metadata.index.dir=/Users/nheron/kie-base-jbpm-710/lucene
-Dorg.uberfire.nio.git.daemon.host=0.0.0.0
-Dorg.guvnor.m2repo.dir=/Users/nheron/kie-base-jbpm-710/M2_REPO
-DM2_HOME=/Users/nheron/.m2/repository
-Dorg.uberfire.nio.git.dir=/Users/nheron/kie-base-jbpm-710/niodir
-Dorg.kie.demo=false
-Djbpm.tsr.jndi.lookup=java:comp/env/TransactionSynchronizationRegistry
-Dhibernate.dialect=org.hibernate.dialect.PostgreSQL82Dialect
-Dorg.kie.server.controller=http://localhost:8080/kie-wb/rest/controller
-Dorg.kie.server.location=http://localhost:8080/kie-server/services/rest/server
-Dorg.chtijbug.server.tracedir=/Users/nheron/kie-base-jbpm-710/trace



----
-Dorg.uberfire.ext.security.management.wildfly.cli.port=11990