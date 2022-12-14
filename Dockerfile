FROM openjdk:11-jre
EXPOSE 8080
ARG JAR_FILE=build/libs/dataflow-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
VOLUME ["/var/log"]
ENTRYPOINT ["java", "-jar", "-Xms2048M", "-Xmx2048M", "/app.jar"]
