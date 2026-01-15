# Utiliser Java 17
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copier le jar construit
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8070

# Commande pour lancer l'application
ENTRYPOINT ["java","-jar","/app/app.jar"]
