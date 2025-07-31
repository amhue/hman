FROM openjdk:24
MAINTAINER amhue
COPY target/hman-0.0.1-SNAPSHOT.jar hman-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/hman-0.0.1-SNAPSHOT.jar"]
