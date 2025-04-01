package com.grunemer.crm.repository;

import com.grunemer.crm.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}
