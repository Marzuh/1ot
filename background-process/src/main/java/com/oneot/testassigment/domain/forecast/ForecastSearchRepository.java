package com.oneot.testassigment.domain.forecast;

import com.oneot.testassigment.mapper.ForecastResponseRecordRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


import static java.lang.String.format;
import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class ForecastSearchRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Page<ForecastResponseRecord> search(ForecastSearchRequest sr) {
        Pageable pageable = PageRequest.of(sr.getPageNumber() - 1, sr.getPageSize());
        Pair<String, MapSqlParameterSource> commonSqlAndParams = buildCommonSqlAndParams(sr);

        String commonSql = commonSqlAndParams.getFirst();
        MapSqlParameterSource params = commonSqlAndParams.getSecond();

        String select = "SELECT f.*, p.name pf_place_name, pf.id pf_id, pf.phenomenon pf_phenomenon, pf.temp_min pf_temp_min, pf.temp_max pf_temp_max" +
                commonSql +
                " ORDER BY f.id " +
                format(" LIMIT %s", pageable.getPageSize()) +
                format(" OFFSET %s", pageable.getOffset());

        List<ForecastResponseRecord> found = namedParameterJdbcTemplate.query(select, params, new ForecastResponseRecordRowMapper());
        Long total = findTotal(commonSql, params);

        return new PageImpl<>(found, pageable, total);
    }

    private static Pair<String, MapSqlParameterSource> buildCommonSqlAndParams(ForecastSearchRequest sr) {
        StringBuilder commonSql = new StringBuilder();
        MapSqlParameterSource params = new MapSqlParameterSource();

        commonSql.append(
                " FROM forecast f " +
                        "LEFT JOIN place_forecast pf ON f.id = pf.forecast_id " +
                        "LEFT JOIN place p ON pf.place_id = p.id " +
                "WHERE 1 = 1 "
        );

        if (sr.getDate() != null) {
            commonSql.append(" AND f.date = :date ");
            params.addValue("date", sr.getDate());
        }
        if (hasText(sr.getPlace())) {
            commonSql.append(" AND p.name = :place ");
            params.addValue("place", sr.getPlace());
        }
        if (hasText(sr.getTimeOfDay()) && List.of(TimeOfDay.DAY.name(), TimeOfDay.NIGHT.name()).contains(sr.getTimeOfDay())) {
            commonSql.append(" AND f.time_of_day = :timeOfDay ");
            params.addValue("timeOfDay", sr.getTimeOfDay());
        }
        return Pair.of(commonSql.toString(), params);
    }

    private Long findTotal(String sql, MapSqlParameterSource params) {
        return namedParameterJdbcTemplate.queryForObject(
                "SELECT COUNT(1) AS row_count " + sql, params,
                Long.class
        );
    }
}
