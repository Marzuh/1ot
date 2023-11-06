package com.oneot.testassigment.domain.forecast;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PlaceForecastResponseRecord {

    private Long pfId;
    private String pfPhenomenon;
    private Integer pfTempMin;
    private Integer pfTempMax;
    private String pfPlaceName;

}
