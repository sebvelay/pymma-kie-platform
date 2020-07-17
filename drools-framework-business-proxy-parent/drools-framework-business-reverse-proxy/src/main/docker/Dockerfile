FROM azul/zulu-openjdk-debian:11


ADD application.properties /application.properties
ADD maven/drools-framework-business-reverse-proxy.jar app.jar



RUN bash -c 'touch /app.jar'

ENV JAVA_OPTS "-Dspring.config.location=file:./ "

ENTRYPOINT exec java $JAVA_OPTS $PYMMA_OPTS -jar /app.jar

#ENTRYPOINT ["java","-Dspring.config.location=file:./","-jar","/app.jar"]
#ENTRYPOINT ["java","-jar","/app.jar"]
