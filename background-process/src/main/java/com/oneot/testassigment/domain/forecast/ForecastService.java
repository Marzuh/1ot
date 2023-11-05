package com.oneot.testassigment.domain.forecast;

import com.oneot.testassigment.domain.place.PlaceService;
import com.oneot.testassigment.domain.place_forecast.PlaceForecast;
import com.oneot.testassigment.weather_api_client.dto.Forecasts;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.oneot.testassigment.mapper.ForecastMapper.mapForecast;

@Service
@AllArgsConstructor
public class ForecastService {

    private final ForecastRepository forecastRepository;

    private final PlaceService placeService;

    private static final Logger logger = LoggerFactory.getLogger(ForecastService.class);

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
        logger.debug("New forecasts saved.");
    }

    private void createAndSaveForecast(com.oneot.testassigment.weather_api_client.dto.Forecast f, LocalDate date, TimeOfDay timeOfDay) {
        Forecast forecast = mapForecast(f.getDay(), date, timeOfDay);
        if (f.getDay().getPlaces() != null) {
            f.getDay().getPlaces().forEach(p -> {
                String placeName = p.getName();
                int placeId = placeService.getPlaceIdByName(placeName);
                PlaceForecast placeForecast = new PlaceForecast()
                        .setForecastId(forecast.getId())
                        .setPlaceId(placeId);
                if (p.getTempMin() != null) {
                    placeForecast.setTempMin(p.getTempMin());
                }
                if (p.getTempMax() != null) {
                    placeForecast.setTempMax(p.getTempMax());
                }
                if (p.getPhenomenon() != null && !p.getPhenomenon().isBlank()) {
                    placeForecast.setPhenomenon(p.getPhenomenon());
                }
                forecast.getPlaces().add(placeForecast);
            });
        }
        forecastRepository.save(forecast);
    }
}
