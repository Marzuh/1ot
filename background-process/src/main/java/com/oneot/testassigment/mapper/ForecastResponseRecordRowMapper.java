package com.oneot.testassigment.mapper;

import com.oneot.testassigment.domain.forecast.ForecastResponseRecord;
import com.oneot.testassigment.domain.forecast.PlaceForecastResponseRecord;
import com.oneot.testassigment.domain.forecast.TimeOfDay;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class ForecastResponseRecordRowMapper implements ResultSetExtractor<List<ForecastResponseRecord>> {

    @Override
    public List<ForecastResponseRecord> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, ForecastResponseRecord> map = new HashMap<>();
        ForecastResponseRecord forecastResponseRecord;

        while (rs.next()) {
            Integer id = rs.getInt("id");
            if (map.get(id) == null) {
                forecastResponseRecord = new ForecastResponseRecord();
                forecastResponseRecord.setId(id);
                forecastResponseRecord.setDate(rs.getObject("date", LocalDate.class));
                forecastResponseRecord.setTimeOfDay(TimeOfDay.valueOf(rs.getString("time_of_day")));
                forecastResponseRecord.setPhenomenon(rs.getString("phenomenon"));
                forecastResponseRecord.setTempMin(rs.getInt("temp_min"));
                forecastResponseRecord.setTempMax(rs.getInt("temp_max"));
                forecastResponseRecord.setTextDescription(rs.getString("text_description"));
                forecastResponseRecord.setPeipsi(rs.getString("peipsi"));
                forecastResponseRecord.setPlaceForecasts(new HashSet<>());
                map.put(id, forecastResponseRecord);
            } else {
                forecastResponseRecord = map.get(id);
            }

            Long pfId = rs.getObject("pf_id", Long.class);
            if (pfId != null) {
                PlaceForecastResponseRecord placeForecastResponseRecord = new PlaceForecastResponseRecord();
                placeForecastResponseRecord.setPfId(pfId);
                placeForecastResponseRecord.setPfPhenomenon(rs.getString("pf_phenomenon"));
                placeForecastResponseRecord.setPfTempMin(rs.getObject("pf_temp_min", Integer.class));
                placeForecastResponseRecord.setPfTempMax(rs.getObject("pf_temp_max", Integer.class));
                placeForecastResponseRecord.setPfPlaceName(rs.getString("pf_place_name"));

                forecastResponseRecord.getPlaceForecasts().add(placeForecastResponseRecord);
            }
        }

        return new ArrayList<>(map.values());
    }
}