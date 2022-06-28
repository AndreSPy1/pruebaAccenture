package com.accenture.pruebaAccenture.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PhotoDTO {
    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;
}
