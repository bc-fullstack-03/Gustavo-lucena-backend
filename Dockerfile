FROM eclipse-temurin:17-jdk-alpine
ADD target/bootcamp-backend*.jar app.jar
ENTRYPOINT ["java","-jar", "app.jar"]