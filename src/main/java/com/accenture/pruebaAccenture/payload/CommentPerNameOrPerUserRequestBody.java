package com.accenture.pruebaAccenture.payload;

import com.accenture.pruebaAccenture.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class CommentPerNameOrPerUserRequestBody {
    private String name;
    private UserDTO user;
}
