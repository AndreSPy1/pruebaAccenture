package com.accenture.pruebaAccenture.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class CommentDTO {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
