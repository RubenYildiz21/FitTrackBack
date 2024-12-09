# Utiliser une image de base Java
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR de votre application dans le conteneur
COPY target/fit-track-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port sur lequel l'application écoute (par défaut 8080 pour Spring Boot)
EXPOSE 8080

# Commande pour démarrer l'application
CMD ["java", "-jar", "app.jar"]