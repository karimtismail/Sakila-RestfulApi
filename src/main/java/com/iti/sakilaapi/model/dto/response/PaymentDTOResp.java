package com.iti.sakilaapi.model.dto.response;

import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class PaymentDTOResp implements Serializable {
    private Integer id;
    private CustomerDTOResp customer;
    private StaffDTOResp staff;
    private RentalDTOResp rental;
    private BigDecimal amount;
    private Instant paymentDate;
    private Instant lastUpdate;
    private List<Link> links;
}
