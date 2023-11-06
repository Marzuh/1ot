package com.oneot.testassigment.domain.forecast;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ForecastSearchRequest {

    private LocalDate date;
    private String place;
    private String timeOfDay;
    private int pageSize;
    private int pageNumber;

}
