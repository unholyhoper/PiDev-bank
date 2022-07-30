FROM openjdk:11-oracle

ADD target/bank-0.0.1-SNAPSHOT.jar app.jar


CMD ["java", "-jar", "/app.jar"]