FROM openjdk:20-ea-19-oracle

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /app

ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]