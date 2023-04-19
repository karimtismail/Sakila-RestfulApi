package com.iti.sakilaapi.model.dto.response;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class RentalDTOResp implements Serializable {
    private Integer id;
    private Instant rentalDate;
    private InventoryDTOResp inventory;
    private CustomerDTOResp customer;
    private Instant returnDate;
    private StaffDTOResp staff;
    private Instant lastUpdate;
}
