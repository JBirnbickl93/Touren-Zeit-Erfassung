package com.birnbickl.Service;

import com.birnbickl.Entity.TourEntity;
import com.birnbickl.Repository.TourRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Diese Klasse beinhaltet Methoden, um die Touren aus der Datenbank auszulesen,
// zu löschen, speichern und ggfs. einzelne Touren zu suchen.

@Service
public class TourService {
private final TourRepository tourRepo;

public TourService(TourRepository tourRepo) {
    this.tourRepo = tourRepo;
}

public List<TourEntity> getAllTours(){
    return tourRepo.findAll();
}

public Optional<TourEntity> getTourById(Integer id){
    return tourRepo.findById(id);
}

public TourEntity saveTour(TourEntity tour){
    return tourRepo.save(tour);
}

public void deleteTour(Integer id){
    tourRepo.deleteById(id);
}

}
