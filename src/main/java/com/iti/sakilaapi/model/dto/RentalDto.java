package com.iti.sakilaapi.model.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class RentalDto implements Serializable {
    private Integer rentalId;
    private Date rentalDate;
    private Date returnDate;
    private Date lastUpdate;
}