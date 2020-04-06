FROM openjdk:8-alpine

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /opt/application

COPY target/ecommerce_api.jar ./ecommerce_api.jar

ENTRYPOINT ["java", "-jar", "xxecommerce_api.jar"]