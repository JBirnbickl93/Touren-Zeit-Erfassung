package com.birnbickl.Mapper;

import com.birnbickl.DTO.TourDTO;
import com.birnbickl.Entity.TourEntity;


// Diese Klasse soll zur Umwandlung von DTO in Entity und umgekehrt dienen. ==> Hilfsklasse
// Dadurch werden DTOs übergeben und nicht die Entitäten aus der Datenbank. (Sicherheitsaspekt)
public class TourMapper {
    public static TourEntity toEntity(TourDTO tourDTO) {
        TourEntity entity = new TourEntity();
        entity.setStartOrt(tourDTO.getStartOrt());
        entity.setEndOrt(tourDTO.getEndOrt());
        entity.setStartZeit(tourDTO.getStartZeit());
        entity.setEndZeit(tourDTO.getEndZeit());
        entity.setMitarbeiterName(tourDTO.getMitarbeiterName());
        entity.setFahrzeugId(tourDTO.getFahrzeugId());
        entity.setTourKommentar(tourDTO.getTourKommentar());
        return entity;
    }

    public static TourDTO toDto(TourEntity tourEntity) {
        TourDTO dto = new TourDTO();
        dto.setStartOrt(tourEntity.getStartOrt());
        dto.setEndOrt(tourEntity.getEndOrt());
        dto.setStartZeit(tourEntity.getStartZeit());
        dto.setEndZeit(tourEntity.getEndZeit());
        dto.setMitarbeiterName(tourEntity.getMitarbeiterName());
        dto.setFahrzeugId(tourEntity.getFahrzeugId());
        dto.setTourKommentar(tourEntity.getTourKommentar());
        return dto;
    }


}
