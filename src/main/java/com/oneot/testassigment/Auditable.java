package com.oneot.testassigment;

import lombok.Data;

import java.time.Instant;

@Data
public class Auditable {

    private Instant createdAt;

    private String createdBy;

    private Instant modifiedAt;

    private String modifiedBy;

}
