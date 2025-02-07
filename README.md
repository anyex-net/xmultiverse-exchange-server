# backend

## clean
mvn clean
## compile
mvn compile -DskipTests=true

## env-dev
mvn clean package -DskipTests=true -Pexchangebiz-dev
## env-test
mvn clean package -DskipTests=true -Pexchangebiz-test
## env-prod
mvn clean package -DskipTests=true -Pexchangebiz-prod
