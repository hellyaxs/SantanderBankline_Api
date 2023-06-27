FROM openjdk:11
ARG JAR_FILE=Bankline.api.jar
ADD ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]



