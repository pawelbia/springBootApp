#Download dependencies
FROM eclipse-temurin:17-jdk-alpine AS dependencies
RUN apk add --no-cache maven
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline

#Build the application
FROM dependencies AS builder
COPY src ./src
RUN mvn clean package -DskipTests

#Run the application
FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]