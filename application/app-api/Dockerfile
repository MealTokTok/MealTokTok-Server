FROM eclipse-temurin:17-jdk-alpine

COPY application/app-api/build/libs/app-api-0.0.1.jar mealtoktok.jar

RUN apk update
RUN apk --no-cache add curl

ENTRYPOINT ["java", "-jar", "mealtoktok.jar"]
