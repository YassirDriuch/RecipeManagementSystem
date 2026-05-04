# recipe-service

Een REST API voor het beheren van recepten. Gebruikers kunnen recepten aanmaken, bewerken, verwijderen en zoeken op naam of categorie. Elk recept is gekoppeld aan de gebruiker die het heeft aangemaakt.

## Functionaliteit

- Registreren en inloggen via HTTP Basic authenticatie
- Recepten aanmaken met naam, categorie, beschrijving, ingredienten en bereidingswijze
- Eigen recepten bewerken en verwijderen
- Zoeken op naam of categorie (hoofdletterongevoelig)

## Technologie

- Java, Spring Boot
- Spring Security met HTTP Basic authenticatie
- Spring Data JPA
- H2 embedded database
- Gradle

## API endpoints

### Gebruikers

| Methode | Endpoint | Toegang |
|---|---|---|
| POST | /api/register | Openbaar |

### Recepten

| Methode | Endpoint | Toegang |
|---|---|---|
| POST | /api/recipe/new | Ingelogd |
| GET | /api/recipe/{id} | Ingelogd |
| PUT | /api/recipe/{id} | Alleen de maker |
| DELETE | /api/recipe/{id} | Alleen de maker |
| GET | /api/recipe/search/?name={naam} | Ingelogd |
| GET | /api/recipe/search/?category={categorie} | Ingelogd |