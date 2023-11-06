package com.oneot.testassigment.domain.place;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Place")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("api/places")
    @Operation(summary = "Get list of all places for which exist at least one forecast.")
    public List<Place> findAllPlaces() {
        return placeService.findAllPlaces();
    }
}
