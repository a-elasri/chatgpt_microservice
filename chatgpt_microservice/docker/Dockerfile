FROM openjdk:17-alpine
EXPOSE 8081
ARG JAR_FILE=chatgpt_microservice-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
