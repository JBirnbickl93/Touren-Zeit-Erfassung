package com.birnbickl.Touren_Zeit_Erfassung.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
public class TourEntity {
    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Start-Ort darf nicht leer sein!")
    private String startOrt;
    @NotBlank(message = "End-Ort darf nicht leer sein!")
    private String endOrt;

    @NotBlank(message = "Start-Zeit darf nicht leer sein!")
    private LocalDateTime startZeit;
    @NotBlank(message = "End-Zeit darf nicht leer sein!")
    private LocalDateTime endZeit;

    @NotBlank(message = "Ein Mitarbeiter muss zugewiesen werden!")
    private String mitarbeiterName;

    @NotBlank(message = "Fahrzeug muss ausgewählt werden!")
    private int fahrzeugId;

    @NotBlank(message = "Tour-Kommentar darf nicht leer sein!")
    private String tourKommentar;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getStartOrt() {
        return startOrt;
    }
    public void setStartOrt(String startOrt) {
        this.startOrt = startOrt;
    }

    public String getEndOrt() {
        return endOrt;
    }
    public void setEndOrt(String endOrt) {
        this.endOrt = endOrt;
    }

    public LocalDateTime getStartZeit() {
        return startZeit;
    }
    public void setStartZeit(LocalDateTime startZeit) {
        this.startZeit = startZeit;
    }

    public LocalDateTime getEndZeit() {
        return endZeit;
    }
    public void setEndZeit(LocalDateTime endZeit) {
        this.endZeit = endZeit;
    }

    public String getMitarbeiterName() {
        return mitarbeiterName;
    }
    public void setMitarbeiterName(String mitarbeiterName) {
        this.mitarbeiterName = mitarbeiterName;
    }

    public int getFahrzeugId() {
        return fahrzeugId;
    }
    public void setFahrzeugId(int fahrzeugId) {
        this.fahrzeugId = fahrzeugId;
    }

    public String getTourKommentar() {
        return tourKommentar;
    }
    public void setTourKommentar(String tourKommentar) {
        this.tourKommentar = tourKommentar;
    }
}
