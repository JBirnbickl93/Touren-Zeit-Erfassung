🚐 Touren- & Zeiterfassungs-Backend (Spring Boot)

Ein praktisches REST-API-Projekt mit Spring Boot, das eine einfache Tourenplanung und Zeiterfassung für Mitarbeiter abbildet. Ideal, um die wichtigsten Backend-Konzepte zu zeigen: saubere API-Architektur, DTO-Nutzung, Validierung, Suche, Fehlerbehandlung und Authentifizierung.

🎯 Ziel des Projekts

Ein Unternehmen (z. B. ein Handwerksbetrieb oder Beförderungsunternehmen) soll Touren für Mitarbeiter erfassen können. Zu jeder Tour gehört:
- Id der Tour
- Start-/End-Ort
- Start- /End-Zeit
- Mitarbeiter
- Fahrzeug
- Kommentar





⚡ Technologien

- Java 17+
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- H2-Datenbank (für Entwicklung)
- Spring Validation (Bean Validation)
- Spring Security + JWT

✍️ Features (MVP)



Tour-Management



Datenmodell 

Tour {
Integer id;
String startOrt;
String endOrt;
LocalDate startZeit;
LocalDate endZeit;
String mitarbeiterName;
Integer fahrzeugId;
String tourKommentar;
}



💡Showcase

Saubere Architektur mit Service, Controller, DTO, Mapper<br>
Validierungen und Fehlermeldungen<br>
REST-konforme HTTP-Codes (201, 400, 404, 204) <br>
Datenbankbindung mit Spring Data JPA<br>
Saubere Fehlerhandling mit @ControllerAdvice<br>
Security (JWT) & Benutzerverwaltung<br>



🏗 Projektstruktur 

src/main/java <br>
├── controller <br>
├── service<br>
├── repository<br>
├── dto<br>
├── entity<br>
├── mapper<br>
└── exception<br>



