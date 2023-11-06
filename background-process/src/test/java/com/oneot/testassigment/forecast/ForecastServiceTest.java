package com.oneot.testassigment.forecast;

import com.oneot.testassigment.common.BackendTest;
import com.oneot.testassigment.domain.forecast.Forecast;
import com.oneot.testassigment.domain.forecast.ForecastService;
import com.oneot.testassigment.domain.forecast.TimeOfDay;
import com.oneot.testassigment.weather_api_client.dto.XmlForecasts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.oneot.testassigment.utils.TestUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ForecastServiceTest extends BackendTest {

    @Autowired
    private ForecastService forecastService;

    @Test
    @DisplayName("Check that forecast mapped and saved correctly.")
    void saveFetchedForecastSuccessfulTest() {

        List<Forecast> forecastBeforeSaving = forecastService.findAll();
        assertThat(forecastBeforeSaving).isEmpty();

        XmlForecasts xmlForecasts = TestUtil.createTestForecasts();

        forecastService.saveFetchedForecasts(xmlForecasts);

        List<Forecast> forecastAfterSaving = forecastService.findAll();

        assertThat(forecastAfterSaving.size()).isEqualTo(2);
        assertThat(forecastAfterSaving).anySatisfy(forecast -> {
                    assertThat(forecast.getDate()).isEqualTo("2023-11-07");
                    assertThat(forecast.getTempMax()).isEqualTo(9);
                    assertThat(forecast.getTempMin()).isEqualTo(1);
                    assertThat(forecast.getPhenomenon()).isEqualTo("Test day phenomenon");
                    assertThat(forecast.getTextDescription()).isEqualTo("Test day text");
                    assertThat(forecast.getPeipsi()).isEqualTo("Test day peipsi");
                    assertThat(forecast.getTimeOfDay()).isEqualTo(TimeOfDay.DAY);
                }
        ).anySatisfy(forecast -> {
                    assertThat(forecast.getDate()).isEqualTo("2023-11-07");
                    assertThat(forecast.getTempMax()).isEqualTo(8);
                    assertThat(forecast.getTempMin()).isEqualTo(2);
                    assertThat(forecast.getPhenomenon()).isEqualTo("Test night phenomenon");
                    assertThat(forecast.getTextDescription()).isEqualTo("Test night text");
                    assertThat(forecast.getPeipsi()).isEqualTo("Test night peipsi");
                    assertThat(forecast.getTimeOfDay()).isEqualTo(TimeOfDay.NIGHT);
                }
        );
    }


}
