package com.oneot.testassigment.domain.forecast;

import com.oneot.testassigment.domain.place.PlaceService;
import com.oneot.testassigment.domain.place_forecast.PlaceForecast;
import com.oneot.testassigment.weather_api_client.dto.Forecasts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.oneot.testassigment.mapper.ForecastMapper.mapForecast;

@Service
@Slf4j
@RequiredArgsConstructor
public class ForecastService {

    private final ForecastRepository forecastRepository;

    private final ForecastSearchRepository forecastSearchRepository;

    private final PlaceService placeService;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

    public void saveFetchedForecasts(Forecasts forecasts) {
        forecasts.getForecastList().forEach(f -> {
            LocalDate date = LocalDate.parse(f.getDate(), formatter);
            // TODO: update if exist and changed
            if (forecastRepository.findByDateAndTimeOfDay(date, TimeOfDay.DAY).isEmpty()) {
                createAndSaveForecast(f, date, TimeOfDay.DAY);
            }
            if (forecastRepository.findByDateAndTimeOfDay(date, TimeOfDay.NIGHT).isEmpty()) {
                createAndSaveForecast(f, date, TimeOfDay.NIGHT);
            }
        });
        log.debug("New forecasts saved.");
    }

    private void createAndSaveForecast(com.oneot.testassigment.weather_api_client.dto.Forecast f, LocalDate date, TimeOfDay timeOfDay) {
        Forecast forecast = mapForecast(f.getDay(), date, timeOfDay);
        if (f.getDay().getPlaces() != null) {
            f.getDay().getPlaces().forEach(p -> {
                String placeName = p.getName();
                int placeId = placeService.getPlaceIdByNameAndSaveIfNotExist(placeName);
                PlaceForecast placeForecast = new PlaceForecast()
                        .setForecastId(forecast.getId())
                        .setPlaceId(placeId)
                        .setTempMin(p.getTempMin())
                        .setTempMax(p.getTempMax())
                        .setPhenomenon(p.getPhenomenon());
                forecast.getPlaces().add(placeForecast);
            });
        }
        forecastRepository.save(forecast);
    }

    public Page<ForecastResponseRecord> searchForecast(ForecastSearchRequest forecastSearchRequest) {
        return forecastSearchRepository.search(forecastSearchRequest);
    }
}
