FROM azul/zulu-openjdk-debian:11


ADD application.properties /application.properties
ADD maven/drools-framework-business-indexer-app.jar app.jar
RUN mkdir /root/.ssh
RUN echo toto > /root/.ssh/known_hosts


RUN bash -c 'touch /app.jar'

ENV JAVA_OPTS " -Dspring.config.location=file:./ "

ENTRYPOINT exec java $JAVA_OPTS $PYMMA_OPTS -jar /app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
