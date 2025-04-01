package com.grunemer.crm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Profile customer;
    private double amount;
    private double outstandingBalance;
    private LocalDate startDate;
}
