package com.oneot.testassigment.domain.forecast;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Forecast")
@RequestMapping("api/forecast")
public class ForecastController {

    private final ForecastService forecastService;

    @GetMapping("search")
    @Operation(summary = "Search forecast for given search parameters")
    public Page<ForecastResponseRecord> searchForecast(ForecastSearchRequest forecastSearchRequest) {
        return forecastService.searchForecast(forecastSearchRequest);
    }

}
