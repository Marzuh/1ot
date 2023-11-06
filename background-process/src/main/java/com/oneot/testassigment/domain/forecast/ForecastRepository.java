package com.oneot.testassigment.domain.forecast;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ForecastRepository extends CrudRepository<Forecast, Integer> {

    Optional<Forecast> findByDateAndTimeOfDay(LocalDate date, TimeOfDay timeOfDay);

    List<Forecast> findAll();
}
