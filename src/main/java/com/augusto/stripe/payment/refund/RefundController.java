package com.augusto.stripe.payment.refund;

import com.stripe.exception.StripeException;
import com.stripe.model.Refund;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payment/refund")
@Slf4j
public class RefundController {

    @Autowired
    RefundService refundService;

    @PostMapping("/{id}")
    public ResponseEntity<Object> create(@PathVariable String id) throws StripeException {
        Refund refund = refundService.create(id);
        return ResponseEntity.status(HttpStatus.OK).body(refund.toJson());
    }

}
