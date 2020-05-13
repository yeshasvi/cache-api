# cache-api
START the service at port 8080

./mvnw spring-boot:run

START the service at given port (for multiple instances)

env SERVER_PORT=8081 ./mvnw spring-boot:run

env SERVER_PORT=8085 ./mvnw spring-boot:run
