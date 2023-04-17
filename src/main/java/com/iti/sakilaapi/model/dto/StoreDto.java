package com.iti.sakilaapi.model.dto;

import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto implements Serializable {
    private Short id;
    //    private String managerName;
    private AddressDto address;
    private Date lastUpdate;
    private List<Link> links;

    public StoreDto(AddressDto address, Date lastUpdate) {
        this.address = address;
        this.lastUpdate = lastUpdate;
    }
}