package com.oneot.testassigment.domain.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public Integer getPlaceIdByNameAndSaveIfNotExist(String name) {
        return placeRepository
                .findByName(name)
                .orElseGet(() -> placeRepository.save(new Place().setName(name)))
                .getId();
    }

    public List<Place> findAllPlaces() {
        return placeRepository.findAll();
    }
}
