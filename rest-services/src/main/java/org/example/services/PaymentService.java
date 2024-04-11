package org.example.services;

import org.example.NotEnoughMoneyException;
import org.example.models.PaymentDetails;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {
    public void processPayment(PaymentDetails paymentDetails) {
        if(paymentDetails.getAmount() > 1000.0) {
            throw new NotEnoughMoneyException();
        }
    }
}
