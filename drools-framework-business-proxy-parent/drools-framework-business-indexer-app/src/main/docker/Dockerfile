FROM openjdk:8-jre-slim


ADD application.properties /application.properties
ADD maven/drools-framework-business-indexer-app-1.1-SNAPSHOT.jar app.jar
RUN mkdir /root/.ssh
RUN echo toto > /root/.ssh/known_hosts


RUN bash -c 'touch /app.jar'

ENTRYPOINT ["java","-Dspring.config.location=file:./","-jar","/app.jar"]
#ENTRYPOINT ["java","-jar","/app.jar"]
