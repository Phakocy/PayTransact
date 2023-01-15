package com.interswitch.paytransact.entities.commons;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {
    private String status;
    private String message;

    public ApiError(String message) {
        this.status = "error";
        this.message = message;
    }
}
