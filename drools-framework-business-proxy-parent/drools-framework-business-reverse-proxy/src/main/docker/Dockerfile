FROM openjdk:8-jre-slim


ADD application.properties /application.properties
ADD maven/drools-framework-business-reverse-proxy.jar app.jar



RUN bash -c 'touch /app.jar'

ENTRYPOINT ["java","-Dspring.config.location=file:./","-jar","/app.jar"]
#ENTRYPOINT ["java","-jar","/app.jar"]
