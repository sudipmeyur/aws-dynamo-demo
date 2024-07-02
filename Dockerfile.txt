FROM maven:3-amazoncorretto-21-al2023 as buildenv

COPY . .

RUN mvn clean package

FROM amazoncorretto:21-alpine3.19

COPY --from=buildenv target/aws-dynamo-demo-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./app.jar"]