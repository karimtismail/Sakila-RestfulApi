package com.iti.sakilaapi.model.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@XmlRootElement
public class InventoryDto implements Serializable {
    private Integer inventoryId;
    private Date lastUpdate;
    private short filmID;
    private String filmName;
}