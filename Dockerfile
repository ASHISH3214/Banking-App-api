FROM eclipse-temurin:17-alpine
VOLUME /target
ADD target/banking-app-0.0.1-SNAPSHOT.jar banking-app-0.0.1-SNAPSHOT.jar
ENTRYPOINT [“java”,“-jar”,“banking-app-0.0.1-SNAPSHOT.jar”]
EXPOSE 8080
