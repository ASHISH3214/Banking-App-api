FROM eclipse-temurin:17-alpine
VOLUME /tmp
COPY /target/banking-app-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT [“java”,“-jar”,“banking-app-0.0.1-SNAPSHOT.jar”]
EXPOSE 8080