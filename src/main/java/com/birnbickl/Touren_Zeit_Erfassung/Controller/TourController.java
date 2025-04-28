package com.birnbickl.Touren_Zeit_Erfassung.Controller;

import com.birnbickl.Touren_Zeit_Erfassung.DTO.TourDTO;
import com.birnbickl.Touren_Zeit_Erfassung.Entity.TourEntity;
import com.birnbickl.Touren_Zeit_Erfassung.Mapper.TourMapper;
import com.birnbickl.Touren_Zeit_Erfassung.Service.TourService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TourController {
    private final TourService tourService;


    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @PostMapping("/tours")
    public ResponseEntity<String> postTours(@Valid @RequestBody TourDTO requestDto) {
        TourEntity entity = TourMapper.toEntity(requestDto);
        TourEntity saved = tourService.saveTour(entity);
        TourDTO responseDto = TourMapper.toDto(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto.toString());

}
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

    @GetMapping("/tours/{id}")
    public ResponseEntity<TourDTO> getTourById(@PathVariable ("id") Integer id) {
        return tourService.getTourById(id)
                .map(TourMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/tours/{id}")
    public ResponseEntity<Void> deleteTourById(@PathVariable ("id") Integer id) {
        if(tourService.getTourById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            tourService.deleteTour(id);
            return ResponseEntity.noContent().build();
        }
    }

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

}


