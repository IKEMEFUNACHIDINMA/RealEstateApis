## Use OpenJDK as the base image
#FROM openjdk:17-jdk-slim
# # Set the working directory
#WORKDIR /app
# # Copy the JAR file into the container
#COPY target/myapp-0.0.1-SNAPSHOT.jar app.jar
# # Expose port 8080
#EXPOSE 8080
# # Run the application
#ENTRYPOINT ["java", "-jar", "app.jar"]

#FROM eclipse-temurin:17-jdk-alpine
#WORKDIR /app
#COPY target/myapp-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]

FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app

# Preload dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -DskipTests

# Copy source and build
COPY . .
RUN mvn clean package

# Stage 2: Lightweight JDK image
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY --from=builder /app/target/*.jar app.jar

# ✅ Activate the 'production' profile
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=production"]

# ✅ Make port visible to Render
EXPOSE 8084