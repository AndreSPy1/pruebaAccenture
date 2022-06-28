package com.accenture.pruebaAccenture.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GeoDTO {
    @JsonIgnore
    private int id;
    private String lat;
    private String lng;
    @JsonIgnore
    private  AddressDTO addressDTO;
}
