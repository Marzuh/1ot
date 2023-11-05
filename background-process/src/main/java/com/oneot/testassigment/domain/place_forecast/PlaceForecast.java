package com.oneot.testassigment.domain.place_forecast;

import com.oneot.testassigment.domain.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("weather\".\"place_forecast")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PlaceForecast extends Auditable {

    @Id
    private Long id;

    private Integer placeId;

    private Integer forecastId;

    private String phenomenon;

    private Integer tempMin;

    private Integer tempMax;

}
