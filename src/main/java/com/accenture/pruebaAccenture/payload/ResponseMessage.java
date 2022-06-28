package com.accenture.pruebaAccenture.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @NoArgsConstructor @ToString
public class ResponseMessage {
    private String message;

    public ResponseMessage(String message){
        this.message = message;
    }
}
