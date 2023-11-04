package com.oneot.testassigment.domain.place_forecast;

import com.oneot.testassigment.domain.place.PlaceRepository;
import org.springframework.data.repository.CrudRepository;

public interface PlaceForecastRepository extends CrudRepository<PlaceRepository, Long> {
}
