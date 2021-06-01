FROM gradle:jdk11-openj9 as build
WORKDIR /workspace/app
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY src src
RUN gradle bootJar

FROM adoptopenjdk/openjdk11:alpine
COPY --from=build /workspace/app/build/libs/*.jar movies.jar
ENTRYPOINT ["java","-jar","movies.jar"]
