FROM eclipse-temurin:17-apline
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT [“java”,“-jar”,“app.jar”]
EXPOSE 8080