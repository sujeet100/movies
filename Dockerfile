FROM gradle:jdk11-openj9 as build
WORKDIR /workspace/app
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY src src
RUN gradle build -x test

FROM adoptopenjdk/openjdk11:alpine
COPY --from=build /workspace/app/build/libs/*.jar build/libs/movies.jar
ENTRYPOINT ["java","-jar","/movies.jar"]
