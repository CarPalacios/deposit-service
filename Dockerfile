FROM openjdk:11
COPY "./target/deposit-service-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8094
ENTRYPOINT ["java","-jar","app.jar"]