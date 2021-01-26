FROM openjdk:8
ADD target/e-house.jar e-house.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.proflies.active=dev", "-Djava.security.egd=file:/dev/./urandom", "e-house.jar"]