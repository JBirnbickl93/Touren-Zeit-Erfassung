package com.birnbickl.Touren_Zeit_Erfassung.DTO;

import java.time.LocalDateTime;

public class TourDTO {

    private String startOrt;
    private String endOrt;
    private LocalDateTime startZeit;
    private LocalDateTime endZeit;
    private String mitarbeiterName;
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
