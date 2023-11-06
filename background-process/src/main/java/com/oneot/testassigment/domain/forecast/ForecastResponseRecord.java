package com.oneot.testassigment.domain.forecast;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Set;

@Data
@Accessors(chain = true)
public class ForecastResponseRecord {

    private Integer id;

    private LocalDate date;

    private TimeOfDay timeOfDay;

    private String phenomenon;

    private int tempMin;

    private int tempMax;

    private Set<PlaceForecastResponseRecord> placeForecasts;

    private String textDescription;

    private String peipsi;
}
