FROM  openjdk:11
VOLUME [ "/tmp"]
EXPOSE 8080
ADD ./target
ENTRYPOINT ["java", "jar", "/backend.jar"]