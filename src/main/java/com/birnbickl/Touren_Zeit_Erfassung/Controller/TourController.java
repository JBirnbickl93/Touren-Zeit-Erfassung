package com.birnbickl.Touren_Zeit_Erfassung.Controller;

import com.birnbickl.Touren_Zeit_Erfassung.DTO.TourDTO;
import com.birnbickl.Touren_Zeit_Erfassung.DTO.TourPatchDTO;
import com.birnbickl.Touren_Zeit_Erfassung.Entity.TourEntity;
import com.birnbickl.Touren_Zeit_Erfassung.Mapper.TourMapper;
import com.birnbickl.Touren_Zeit_Erfassung.Service.TourService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

// Controller Klasse in der alle Tour Angelegenheiten entgegengenommen werden

@RestController
public class TourController {
    private final TourService tourService;


    public TourController(TourService tourService) {
        this.tourService = tourService;
    }


    // Methode um neue Tour zu erstellen
    @PostMapping("/tours")
    public ResponseEntity<TourDTO> postTours(@Valid @RequestBody TourDTO requestDto) {
        TourEntity entity = TourMapper.toEntity(requestDto);
        TourEntity saved = tourService.saveTour(entity);
        TourDTO responseDto = TourMapper.toDto(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

}

    // Methode um alle Touren zu erhalten
    @GetMapping("/tours")
    public ResponseEntity<List<TourDTO>> getAllTours() {
        List<TourEntity> tourList = tourService.getAllTours();

        if (tourList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else{
            List<TourDTO> tourDtoList = tourList.stream()
                    .map(TourMapper::toDto).toList();
            return ResponseEntity.ok(tourDtoList);
        }
    }

    // Methode um eine Tour aus Datenbank zu erhalten
    @GetMapping("/tours/{id}")
    public ResponseEntity<TourDTO> getTourById(@PathVariable ("id") Integer id) {
        return tourService.getTourById(id)
                .map(TourMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Methode um eine Tour zu löschen
    @DeleteMapping("/tours/{id}")
    public ResponseEntity<Void> deleteTourById(@PathVariable ("id") Integer id) {
        if(tourService.getTourById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            tourService.deleteTour(id);
            return ResponseEntity.noContent().build();
        }
    }

    // Methode um eine Tour komplett zu ersetzen
    @PutMapping("/tours/{id}")
    public ResponseEntity<TourDTO> putTourById(@PathVariable Integer id, @Valid @RequestBody TourDTO requestDto) {
        if(tourService.getTourById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            TourEntity entity = TourMapper.toEntity(requestDto);
            entity.setId(id);
            tourService.saveTour(entity);
            TourDTO responseDto = TourMapper.toDto(entity);
            return ResponseEntity.ok(responseDto);
        }
    }

    // Hilfsmethode, um ein Optional zu überprüfen und den Wert zu setzen
    private <T> void updateIfPresent(Optional<T> value, Consumer<T> consumer) {
        value.ifPresent(consumer);
    }

    // Methode um einzelne Teile einer Tour anzupassen
    @PatchMapping("/tours/{id}")
    public ResponseEntity<TourDTO> patchTourById(@PathVariable Integer id, @RequestBody TourPatchDTO patchDto) {
        Optional<TourEntity> optionalEntity = tourService.getTourById(id);


        if (optionalEntity.isPresent()) {
            TourEntity entity = optionalEntity.get();

        // Verwenden der Hilfsmethode, um die Felder zu aktualisieren
        updateIfPresent(patchDto.getStartOrt(), entity::setStartOrt);
        updateIfPresent(patchDto.getEndOrt(), entity::setEndOrt);
        updateIfPresent(patchDto.getStartZeit(), entity::setStartZeit);
        updateIfPresent(patchDto.getEndZeit(), entity::setEndZeit);
        updateIfPresent(patchDto.getMitarbeiterName(), entity::setMitarbeiterName);
        updateIfPresent(patchDto.getFahrzeugId(), entity::setFahrzeugId);
        updateIfPresent(patchDto.getTourKommentar(), entity::setTourKommentar);

        tourService.saveTour(entity);
        TourDTO responseDto = TourMapper.toDto(entity);
        return ResponseEntity.ok(responseDto);
    } else {
            return ResponseEntity.notFound().build();
        }

    }

}


