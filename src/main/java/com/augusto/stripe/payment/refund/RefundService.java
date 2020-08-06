package com.augusto.stripe.payment.refund;

import com.augusto.stripe.payment.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Refund;
import com.stripe.param.RefundCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RefundService {

    @Value("${stripe.secretKey}")
    private String secretKey;

    @Autowired
    @Lazy
    PaymentService paymentService;

    public Refund create(String id) throws StripeException {
        Stripe.apiKey = secretKey;

        RefundCreateParams params =
                RefundCreateParams.builder()
                    .setPaymentIntent(id)
                    .build();

        Refund refund = Refund.create(params);
        log.info(refund.toJson());

        return refund;
    }

    public Refund get(String id) throws StripeException {
        Stripe.apiKey = secretKey;

        Refund refund = Refund.retrieve(id);

        log.info(refund.toJson());
        return refund;
    }
}
