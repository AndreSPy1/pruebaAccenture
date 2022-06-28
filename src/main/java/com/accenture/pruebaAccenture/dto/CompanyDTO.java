package com.accenture.pruebaAccenture.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CompanyDTO {
    @JsonIgnore
    private int id;
    private String name;
    private String catchPhrase;
    private String bs;
    @JsonIgnore
    private UserDTO userDTO;
}
