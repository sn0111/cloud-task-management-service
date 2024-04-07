package com.pesto.tech.util;

import com.pesto.tech.data.dto.ResponseDTO;
import org.springframework.http.HttpStatus;

public class ResponseBuilder {

    private ResponseBuilder() {
    }

    public static ResponseDTO buildSuccessResponse(Object object) {
        ResponseDTO dto = new ResponseDTO();
        dto.setStatus("1");
        dto.setErrorCode("");
        dto.setErrorMessage("");
        dto.setData(object);
        return dto;
    }

    public static ResponseDTO buildErrorResponse(String errorCode, Object errorMessage) {
        ResponseDTO dto = new ResponseDTO();
        dto.setStatus("0");
        dto.setErrorCode(errorCode);
        dto.setErrorMessage(errorMessage);
        dto.setData(null);
        return dto;
    }

    public static ResponseDTO buildErrorResponse(String errorCode) {
        ResponseDTO dto = new ResponseDTO();
        dto.setStatus("0");
        dto.setErrorCode(errorCode);
        dto.setErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        dto.setData(null);
        return dto;
    }
}
