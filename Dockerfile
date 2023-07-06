FROM openjdk:20

COPY target/backend-zoona-0.0.1-SNAPSHOT.jar backend-zoona.jar

ENTRYPOINT [ "java", "-jar", "/backend-zoona.jar"]