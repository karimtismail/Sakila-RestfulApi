package com.iti.sakilaapi.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class StoreDTOResp implements Serializable {
    private Integer id;
//    @JsonProperty
    private StaffDTOResp managerStaff;
//    @JsonProperty
    private AddressDTOResp address;
    private Instant lastUpdate;
    private List<Link> links;
}
