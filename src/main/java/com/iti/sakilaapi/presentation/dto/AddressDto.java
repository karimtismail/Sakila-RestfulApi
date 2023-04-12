package com.iti.sakilaapi.presentation.dto;

import com.iti.sakilaapi.data.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link Address} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AddressDto implements Serializable {
    private Integer id;
    private String address;
    private String address2;
    private String district;
    private String postalCode;
    private String phone;
    private Instant lastUpdate;
    private Object location;
}