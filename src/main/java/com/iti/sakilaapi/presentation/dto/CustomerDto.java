package com.iti.sakilaapi.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.iti.sakilaapi.data.entity.Customer} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CustomerDto implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean active = false;
    private Instant createDate;
    private Instant lastUpdate;
}