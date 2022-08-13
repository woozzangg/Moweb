FROM gradle:jdk17 AS builder

WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew clean build

FROM openjdk:17
COPY --from=builder /app/build/libs/*.jar app.jar
RUN mkdir /images
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar" ]