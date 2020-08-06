package com.augusto.stripe.payment.intent;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payment/intent")
@Slf4j
public class PaymentIntentController {

    @Autowired
    PaymentIntentService paymentIntentService;

    @PostMapping("/{id}")
    public ResponseEntity<Object> create(@PathVariable Long id) throws StripeException {
        PaymentIntent paymentIntent = paymentIntentService.create(id);
        return ResponseEntity.status(HttpStatus.OK).body(paymentIntent.toJson());
    }

}
