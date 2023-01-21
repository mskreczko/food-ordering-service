FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

CMD ["mvn", "-DMaven.test.skip", "install"]

COPY ./target/foodordering-0.0.1-SNAPSHOT.jar foodordering-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "foodordering-0.0.1-SNAPSHOT.jar"]
