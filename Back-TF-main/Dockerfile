FROM openjdk:11
VOLUME [ "/tmp" ]
EXPOSE 8080
ADD ./target/backend.jar backend-aws.jar
ENTRYPOINT ["java", "-jar", "/backend-aws.jar"]