package com.oneot.testassigment.domain.place;

import com.oneot.testassigment.domain.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("weather\".\"place")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Place extends Auditable {

    @Id
    private Integer id;

    private String name;
}
