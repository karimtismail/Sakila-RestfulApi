package com.iti.sakilaapi.model.dto.requests;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@XmlRootElement
public class RentalDTOReq implements Serializable {
    private Instant rentalDate;
    private Integer inventoryId;
    private Integer customerId;
    private Instant returnDate;
    private Integer staffId;
    private Instant lastUpdate;
}
