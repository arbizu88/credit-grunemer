package com.grunemer.crm.controller;


import com.grunemer.crm.entity.Credit;
import com.grunemer.crm.dto.CreditRequest;
import com.grunemer.crm.dto.PaymentRequest;
import com.grunemer.crm.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = "/credit")
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    @GetMapping("/today")
    public List<Credit> getAccountReceivableToday() {
        return creditService.getAccountsReceivableToday();
    }

    @GetMapping
    public List<Credit> getAllCredits() {
        return creditService.getAllCredits();
    }

    @GetMapping(value = "/{id}")
    public Credit getCredit(@PathVariable Long id) {
        return creditService.findCreditById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/register-payment")
    public void registerPayment(@RequestBody PaymentRequest request) {
        if (Objects.isNull(request.getCreditId())) {
            throw new IllegalArgumentException("Id must be provided");
        }
        creditService.registerPayment(request.getCreditId(), request.getAmount());
    }

    @PostMapping
    public Credit createCredit(@RequestBody CreditRequest request) {
        return creditService.createCredit(request.getCustomerId(), request.getAmount());
    }

}
