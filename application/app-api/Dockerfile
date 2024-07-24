FROM eclipse-temurin:17-jdk-alpine

# 빌드 컨텍스트의 경로를 맞추어 COPY 명령을 사용
COPY application/app-api/build/libs/app-api-0.0.1.jar mealtoktok.jar

ENTRYPOINT ["java", "-jar", "mealtoktok.jar"]
