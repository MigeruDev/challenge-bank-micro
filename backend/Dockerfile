# backend/Dockerfile
FROM eclipse-temurin:21-jre-alpine

ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar

# Exponemos el puerto que usa Spring Boot
EXPOSE 8080

# Arranque en modo prod (desactivar devtools, etc.)
ENTRYPOINT ["java","-jar","/opt/app/app.jar"]
