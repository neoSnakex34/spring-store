FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY pom.xml .

RUN ./mvnw clean package

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
