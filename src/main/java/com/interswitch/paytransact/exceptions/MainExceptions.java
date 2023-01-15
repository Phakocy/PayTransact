package com.interswitch.paytransact.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MainExceptions extends RuntimeException {
    private String message;
}
