FROM openjdk:8-jre-slim


ADD application.properties /application.properties
ADD maven/drools-framework-admin-console.jar app.jar

EXPOSE 8200

RUN bash -c 'touch /app.jar'
ENV JAVA_OPTS "-Dspring.config.location=file:./ "

ENTRYPOINT exec java $JAVA_OPTS $PYMMA_OPTS -jar /app.jar
#ENTRYPOINT ["java","-Dspring.config.location=file:./","-jar","/app.jar"]
#ENTRYPOINT ["java","-jar","/app.jar"]
