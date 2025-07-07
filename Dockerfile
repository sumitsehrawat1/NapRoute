FROM maven:3.9-eclipse-temurin-17-alpine AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

COPY deploy.sh /deploy.sh

RUN chmod +x /deploy.sh

EXPOSE 8080

ENTRYPOINT ["/deploy.sh"]