FROM maven:3-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml /app
RUN mvn dependency:resolve
COPY . /app
RUN mvn clean
RUN mvn package -DskipTests -X

FROM openjdk:17-ea-3-jdk-slim
COPY --from=build /app/target/*jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]