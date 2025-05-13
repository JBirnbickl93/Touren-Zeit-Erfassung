package com.birnbickl.DTO;

import java.time.LocalDateTime;
import java.util.Optional;

// "Hilfsklasse" um bei PATCH mit einem DTO zu arbeiten, dass Optionals zulässt,
// in normaler Touren-Klasse (egal ob DTO oder Enity) muss Wert vorhanden sein beim Anlegen.

public class TourPatchDTO {

    private Optional<String> startOrt;
    private Optional<String> endOrt;
    private Optional<LocalDateTime> startZeit;
    private Optional<LocalDateTime> endZeit;
    private Optional<String> mitarbeiterName;
    private Optional<Integer> fahrzeugId;
    private Optional<String> tourKommentar;


    public Optional<String> getStartOrt() {
        return startOrt;
    }
    public void setStartOrt(Optional<String> startOrt) {
        this.startOrt = startOrt;
    }

    public Optional<String> getEndOrt() {
        return endOrt;
    }
    public void setEndOrt(Optional<String> endOrt) {
        this.endOrt = endOrt;
    }

    public Optional<LocalDateTime> getStartZeit() {
        return startZeit;
    }
    public void setStartZeit(Optional<LocalDateTime> startZeit) {
        this.startZeit = startZeit;
    }

    public Optional<LocalDateTime> getEndZeit() {
        return endZeit;
    }
    public void setEndZeit(Optional<LocalDateTime> endZeit) {
        this.endZeit = endZeit;
    }

    public Optional<String> getMitarbeiterName() {
        return mitarbeiterName;
    }
    public void setMitarbeiterName(Optional<String> mitarbeiterName) {
        this.mitarbeiterName = mitarbeiterName;
    }

    public Optional<Integer> getFahrzeugId() {
        return fahrzeugId;
    }
    public void setFahrzeugId(Optional<Integer> fahrzeugId) {
        this.fahrzeugId = fahrzeugId;
    }

    public Optional<String> getTourKommentar() {
        return tourKommentar;
    }
    public void setTourKommentar(Optional<String> tourKommentar) {
        this.tourKommentar = tourKommentar;
    }
}
