package com.oneot.testassigment.domain.place;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public Integer getPlaceIdByName(String name) {
        return placeRepository
                .findByName(name)
                .orElseGet(() -> placeRepository.save(new Place().setName(name)))
                .getId();
    }
}
