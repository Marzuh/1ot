package com.oneot.testassigment.domain.place;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlaceRepository extends CrudRepository<Place, Integer> {

    Optional<Place> findByName(String name);
}
