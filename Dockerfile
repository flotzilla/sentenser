FROM openjdk:12-jdk-alpine
COPY sentenser-0.0.1.jar sentenser-0.0.1.jar
CMD ["java","-jar","sentenser-0.0.1.jar"]