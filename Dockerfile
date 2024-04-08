# Use a base image with Java 17 and Alpine Linux
FROM adoptopenjdk/openjdk17:alpine

WORKDIR /app

COPY target/rbychatserver-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
