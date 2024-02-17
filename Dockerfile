FROM amazoncorretto:21-alpine-jdk
ARG JAR_FILE=target/*.jar
COPY ./target/counter-0.0.1.jar counter.jar
ENTRYPOINT ["java","-jar","/counter.jar"]