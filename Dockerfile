FROM adoptopenjdk/openjdk11:alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} movies.jar
ENTRYPOINT ["java","-jar","/movies.jar"]
