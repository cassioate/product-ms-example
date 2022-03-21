FROM openjdk:11-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN apt-get update && apt-get install -y wget
ENTRYPOINT ["java", "-jar", "app.jar"]