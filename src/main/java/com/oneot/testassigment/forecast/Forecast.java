package com.oneot.testassigment.forecast;

import com.oneot.testassigment.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table("weather\".\"forecast")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Forecast extends Auditable {

    @Id
    private Integer id;

    private LocalDate date;

    private TimeOfDay timeOfDay;

    private String phenomenon;

    private int tempMin;

    private int tempMax;

    private String text_description;

    private String peipsi;
}


