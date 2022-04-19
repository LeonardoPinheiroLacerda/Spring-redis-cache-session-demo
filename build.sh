docker-compose down
docker rmi spring-redis-demo:0.0.1-SNAPSHOT
./mvnw spring-boot:build-image -DskipTests
docker-compose up -d