# API - AggroConnect

API RESTful pour gérer les employés, les service et les sites. Cette API est utilisée par l'application **AggroConnect** pour la gestion des employés.


### Pré-requis
- Java 21
- Maven 3.9.9
- Base de données accessible (extract .sql disponible dans resources/db)

### Cloner le projet
```bash
git clone https://github.com/gaellerem/AggroConnect_BACK.git
cd AggroConnect_BACK
```

configurer application.properties pour la connection avec la BDD

```bash
mvn spring-boot:run
```

### Installation & Exécution
```bash
mvn clean package
java -jar target/api-0.0.1-SNAPSHOT.jar


