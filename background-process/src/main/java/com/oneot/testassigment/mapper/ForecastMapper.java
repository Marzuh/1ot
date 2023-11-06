package com.oneot.testassigment.mapper;

import com.oneot.testassigment.domain.forecast.Forecast;
import com.oneot.testassigment.domain.forecast.TimeOfDay;
import com.oneot.testassigment.weather_api_client.dto.BaseForecast;

import java.time.LocalDate;
import java.util.HashSet;

public class ForecastMapper {

    public static Forecast mapForecast(BaseForecast forecast, LocalDate date, TimeOfDay timeOfDay) {
        return new Forecast()
                .setDate(date)
                .setTimeOfDay(timeOfDay)
                .setPhenomenon(forecast.getPhenomenon())
                .setTempMin(forecast.getTempMin())
                .setTempMax(forecast.getTempMax())
                .setTextDescription(forecast.getText())
                .setPeipsi(forecast.getPeipsi())
                .setPlaces(new HashSet<>());
    }
}
