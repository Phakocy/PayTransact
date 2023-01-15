package com.interswitch.paytransact.entities.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
    private String status;
    private String message;
    private Object data;

    public ApiResponse(Object data, String message) {
        this.status = "success";
        this.message = message;
        this.data = data;
    }

    public ApiResponse(Object data) {
        this.status = "success";
        this.message = "Done successfully";
        this.data = data;
    }

    public ApiResponse(String message) {
        this.status = "success";
        this.message = message;
    }
}
