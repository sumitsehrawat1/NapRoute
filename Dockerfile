FROM maven:3.9-eclipse-temurin-17-alpine AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

# Set up working directory
WORKDIR /app

# Copy built JAR from previous stage
COPY --from=build /app/target/*.jar app.jar

RUN apk add --no-cache bash postgresql postgresql-contrib su-exec

ENV POSTGRES_USER=root
ENV POSTGRES_PASSWORD=root
ENV POSTGRES_DB=naproute

RUN mkdir -p /var/lib/postgresql/data && chown -R postgres:postgres /var/lib/postgresql

WORKDIR /app

COPY deploy.sh /deploy.sh

RUN chmod +x /deploy.sh

EXPOSE 5432 8080

ENTRYPOINT ["/deploy.sh"]