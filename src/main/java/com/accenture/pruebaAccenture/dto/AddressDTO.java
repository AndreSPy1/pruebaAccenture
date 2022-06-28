package com.accenture.pruebaAccenture.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AddressDTO {
    @JsonIgnore
    private int id;
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoDTO geo;
    @JsonIgnore
    private UserDTO userDTO;
}