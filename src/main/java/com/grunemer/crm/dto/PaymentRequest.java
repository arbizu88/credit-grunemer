package com.grunemer.crm.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long creditId;
    private double amount;
}
