package com.grunemer.crm.service;

import com.grunemer.crm.entity.Credit;
import com.grunemer.crm.entity.Payment;
import com.grunemer.crm.entity.Profile;
import com.grunemer.crm.repository.CreditRepository;
import com.grunemer.crm.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final ProfileService profileService;
    private final CreditRepository creditRepo;
    private final PaymentRepository paymentRepo;

    public void registerPayment(Long creditId, double amount) {
        Credit credit = creditRepo.findById(creditId).orElseThrow();
        Payment payment = new Payment();
        payment.setCredit(credit);
        payment.setPaymentDate(LocalDate.now());
        payment.setAmountPaid(amount);
        paymentRepo.save(payment);

        credit.setOutstandingBalance(credit.getOutstandingBalance() - amount);
        creditRepo.save(credit);
    }

    public List<Credit> getAccountsReceivableToday() {
        return creditRepo.findAll().stream()
                .filter(credit -> credit.getOutstandingBalance() > 0 && credit.getStartDate().plusMonths(1).isEqual(LocalDate.now()))
                .toList();
    }

    public List<Credit> getAllCredits() {
        return creditRepo.findAll().stream()
                .toList();
    }

    public Optional<Credit> findCreditById(Long id){
        return creditRepo.findById(id);
    }

    public Credit createCredit(Long customerId, double amount) {
        Profile customer = profileService.findById(customerId).orElseThrow();
        Credit credit = new Credit();
        credit.setCustomer(customer);
        credit.setAmount(amount);
        credit.setOutstandingBalance(amount);
        credit.setStartDate(LocalDate.now());
        return creditRepo.save(credit);
    }
}
