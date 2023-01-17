package com.interswitch.paytransact.dtos;

import lombok.Getter;

@Getter
public class PaymentDto {
    private Long cardNumber;
    private Long accountNumber;
    private Double amount;
    private String narration;
}
