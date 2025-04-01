package com.grunemer.crm.dto;

import lombok.Data;

@Data
public class CreditRequest {
    private Long customerId;
    private double amount;
}
