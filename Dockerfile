# Build app
FROM maven:3.9.0-eclipse-temurin-19-focal AS MAVEN_BUILD
WORKDIR /app
COPY . /app
RUN mvn clean install

# Run app in Docker container
FROM openjdk:19
WORKDIR /app
COPY --from=MAVEN_BUILD /app/target/storageservice-app.jar /app
CMD ["java", "-jar", "storageservice-app.jar"]

