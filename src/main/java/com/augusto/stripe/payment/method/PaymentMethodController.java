package com.augusto.stripe.payment.method;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payment/method")
@Slf4j
public class PaymentMethodController {

    @Autowired
    PaymentMethodService paymentMethodService;

    @PostMapping("/{id}")
    public ResponseEntity<Object> create(@PathVariable Long id) throws StripeException {
        PaymentMethod paymentMethod = paymentMethodService.create(id);
        return ResponseEntity.status(HttpStatus.OK).body(paymentMethod.toJson());
    }

}
