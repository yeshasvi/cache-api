# cache-api
START the service at port 8080

./mvnw spring-boot:run

START the service at given port (for multiple instances)

env SERVER_PORT=8081 ./mvnw spring-boot:run

env SERVER_PORT=8085 ./mvnw spring-boot:run

API DOC and TESTING

http://localhost:8080/swagger-ui.html
http://localhost:8081/swagger-ui.html (for multiple instances as per given ports)
