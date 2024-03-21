
FROM eclipse-temurin:17-alpine
COPY --from=build /target/backing-app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9096
ENTRYPOINT [“java”,“-jar”,“app.jar”]