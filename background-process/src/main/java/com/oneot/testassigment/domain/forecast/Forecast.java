package com.oneot.testassigment.domain.forecast;

import com.oneot.testassigment.domain.Auditable;
import com.oneot.testassigment.domain.place_forecast.PlaceForecast;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Set;

@Data
@Table("weather\".\"forecast")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Forecast extends Auditable {

    @Id
    private Integer id;

    private LocalDate date;

    private TimeOfDay timeOfDay;

    private String phenomenon;

    private int tempMin;

    private int tempMax;

    @MappedCollection(idColumn = "forecast_id")
    private Set<PlaceForecast> places;

    private String text_description;

    private String peipsi;
}


