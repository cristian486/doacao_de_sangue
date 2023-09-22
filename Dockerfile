FROM eclipse-temurin:17-alpine
WORKDIR /app/
COPY target/blooddonation.jar blooddonation.jar
ENV SPRING_PROFILES_ACTIVE=prod
ENV DATABASE_HOST=localhost
ENV DATABASE_PORT=27017
ENV DATABASE_DATABASE=blooddonation
ENV DATABASE_USERNAME=root
ENV DATABASE_PASSWORD=QmwwT2QwTjR0MW9OCg
ENV SECURITY_JWT_SECRET=bVlqczBOdzNidE9LZU5TM2NyZUNUR2VuZXJhdGlvbgo
ENTRYPOINT ["java", "-jar", "blooddonation.jar"]