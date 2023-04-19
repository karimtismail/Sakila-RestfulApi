package com.iti.sakilaapi.model.dto.requests;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@XmlRootElement
public class RentalDTOReq implements Serializable {
    private Integer inventoryId;
    private String customerFirstName;
    private String returnDateInstance;
    private String staffFirstName;
}
