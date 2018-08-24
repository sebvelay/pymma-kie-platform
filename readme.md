

-Xms1536m
-Xmx6536m
-XX:NewSize=256m
-XX:MaxNewSize=256m
-XX:PermSize=256m
-XX:MaxPermSize=556m
-XX:+DisableExplicitGC
-Dhibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
-Dorg.kie.example.repositories=/Users/nheron/kie-base-enterprise/example-import
-Dorg.kie.example=true
-Dorg.uberfire.metadata.index.dir=/Users/nheron/kie-base-enterprise/lucene
-Dorg.uberfire.nio.git.daemon.host=0.0.0.0
-Dorg.guvnor.m2repo.dir=/Users/nheron/.m2/repository
-DM2_HOME==/Users/nheron/.m2/repository
-Dorg.uberfire.nio.git.dir=/Users/nheron/kie-base-enterprise/niodir
-Dorg.kie.demo=false
-Djbpm.tsr.jndi.lookup=java:comp/env/TransactionSynchronizationRegistry
-Dhibernate.dialect=org.hibernate.dialect.PostgreSQL82Dialect



----
-Dorg.uberfire.ext.security.management.wildfly.cli.port=11990