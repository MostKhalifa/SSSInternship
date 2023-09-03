From openjdk:17
ARG JAR_FILE=target/*.jar
COPY ./target/user-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]