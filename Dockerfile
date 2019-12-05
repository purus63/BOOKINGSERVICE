FROM openjdk:12
WORKDIR usr/src
ADD ./target/demo-0.0.1-SNAPSHOT.jar /usr/src/demo-0.0.1-SNAPSHOT.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar","/usr/src/demo-0.0.1-SNAPSHOT.jar"]
