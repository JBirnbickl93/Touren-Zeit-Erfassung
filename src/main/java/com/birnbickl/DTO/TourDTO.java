package com.birnbickl.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

// "Hilfsklasse" um mit DTOs anstatt mit der Entität zu arbeiten

public class TourDTO {

    @NotBlank(message = "StartOrt darf nicht leer sein!")
    private String startOrt;
    @NotBlank(message = "EndOrt darf nicht leer sein!")
    private String endOrt;

    @NotNull(message = "StartZeit muss angegeben werden!")
    private LocalDateTime startZeit;
    @NotNull(message = "EndZeit muss angegeben werden!")
    private LocalDateTime endZeit;

    @NotBlank(message = "MitarbeiterName darf nicht leer sein!")
    @Size(max = 100, message ="MitarbeiterName darf maximal 100 Zeichen lang sein!")
    private String mitarbeiterName;

    @NotNull(message = "FahrzeugId muss angegeben werden!")
    private Integer fahrzeugId;
    private String tourKommentar;

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

    public Integer getFahrzeugId() {
        return fahrzeugId;
    }
    public void setFahrzeugId(Integer fahrzeugId) {
        this.fahrzeugId = fahrzeugId;
    }

    public String getTourKommentar() {
        return tourKommentar;
    }
    public void setTourKommentar(String tourKommentar) {
        this.tourKommentar = tourKommentar;
    }
}
