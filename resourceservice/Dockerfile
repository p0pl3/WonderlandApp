FROM openjdk:21
WORKDIR /app
RUN mkdir "images"
COPY /build/libs/java-kursach-1.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
