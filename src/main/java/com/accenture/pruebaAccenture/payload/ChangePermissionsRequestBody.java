package com.accenture.pruebaAccenture.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ChangePermissionsRequestBody {
    private int userId;
    private int albumId;
    private boolean reading;
    private boolean writing;
}
