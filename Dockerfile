#FROM eclipse-temurin:17-jdk-alpine
#WORKDIR /app
#COPY target/myapp-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]

 # Use OpenJDK as the base image
FROM openjdk:17-jdk-slim
 # Set the working directory
WORKDIR /app
 # Copy the JAR file into the container
COPY target/myapp-0.0.1-SNAPSHOT.jar app.jar
 # Expose port 8080
EXPOSE 8080
 # Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]