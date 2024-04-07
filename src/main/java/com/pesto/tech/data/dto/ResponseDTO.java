package com.pesto.tech.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class ResponseDTO {
    private String status; // 1 or 0
    private String errorCode;
    private Object errorMessage;
    private Object data; // json response
}
