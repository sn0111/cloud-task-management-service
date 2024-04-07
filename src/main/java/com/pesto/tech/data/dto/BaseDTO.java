package com.pesto.tech.data.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BaseDTO {
    private String createdDate;
    private String updatedDate;
    private Long createdBy;
    private Long updatedBy;
}
