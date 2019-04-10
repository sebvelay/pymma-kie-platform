FROM openjdk:8-jre-slim

EXPOSE 9080
EXPOSE 8090
ADD application.properties /application.properties
ADD maven/swimmingpool-web.jar app.jar
USER root
RUN bash -c 'touch /app.jar'
ENV JAVA_OPTS "-Dspring.config.location=file:./ "

ENTRYPOINT exec java $JAVA_OPTS $PYMMA_OPTS -jar /app.jar