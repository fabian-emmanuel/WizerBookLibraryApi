#DockerFile Setup
FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/wizer-lib.jar wizer-lib.jar
ENTRYPOINT ["java", "-jar", "wizer-lib.jar"]
