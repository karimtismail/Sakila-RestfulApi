package com.iti.sakilaapi.model.dto.requests;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@XmlRootElement
public class InventoryDTOReq implements Serializable {
    private Integer filmId;
    private Integer storeId;
    private Instant lastUpdate;
}
