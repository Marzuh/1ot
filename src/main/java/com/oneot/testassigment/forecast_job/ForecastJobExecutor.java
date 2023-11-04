package com.oneot.testassigment.forecast_job;

import com.oneot.testassigment.domain.forecast.ForecastService;
import com.oneot.testassigment.weather_api_client.WeatherApiClient;
import com.oneot.testassigment.weather_api_client.dto.Forecasts;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@EnableScheduling
public class ForecastJobExecutor {

    private final ForecastService forecastService;

    private final WeatherApiClient weatherApiClient;

    private static final Logger logger = LoggerFactory.getLogger(ForecastJobExecutor.class);

    @Scheduled(cron = "${forecast-job-scheduler.cron-expression}", zone = "UTC")
    @SchedulerLock(name = "fetchApiForecast")
    public void fetchForecasts() {
        logger.info("Scheduled job with name = \"fetchApiForecast\" started.");
        Forecasts forecasts = weatherApiClient.fetchAndUpdateForecasts();
        forecastService.saveFetchedForecasts(forecasts);
    }
}
