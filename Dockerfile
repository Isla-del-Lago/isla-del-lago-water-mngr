FROM amazoncorretto:11-alpine

EXPOSE 8080

WORKDIR /app

COPY ./target/watercalculator-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "watercalculator-0.0.1-SNAPSHOT.jar"]