# Étape 1 : Construire l'application avec Maven
FROM maven:3.8.3-openjdk-17 AS build
# Définir le répertoire de travail
WORKDIR /app
# Copier le fichier pom.xml et les fichiers de configuration Maven
COPY pom.xml .
COPY src ./src
# Télécharger les dépendances Maven et construire le projet
RUN mvn clean package -DskipTests

# Étape 2 : Préparer l'image finale
FROM eclipse-temurin:17-jdk
# Définir le répertoire de travail
WORKDIR /app
# Copier le fichier JAR généré par Maven depuis l'étape de construction
COPY --from=build /app/target/*.jar app.jar
# Exposer le port sur lequel votre application Spring Boot tourne (par défaut 8080)
EXPOSE 8080
# Définir la commande par défaut pour exécuter le JAR
ENTRYPOINT ["java", "-jar", "app.jar"]