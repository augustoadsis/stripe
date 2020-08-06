package com.augusto.stripe.payment.intent;

import com.augusto.stripe.payment.Payment;
import com.augusto.stripe.payment.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentIntentService {

    @Value("${stripe.secretKey}")
    private String secretKey;

    @Autowired
    @Lazy
    PaymentService paymentService;

    public PaymentIntent create(Long id) throws StripeException {

        Payment payment = paymentService.findById(id);

        Stripe.apiKey = secretKey;

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(100L)
                        .setCurrency("brl")
                        .addPaymentMethodType("card")
                        .setPaymentMethod(payment.getStripeId())
                        .setCustomer(payment.getUser().getStripeId())
                        .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        paymentIntent = paymentIntent.confirm();
        log.info(paymentIntent.toJson());

        return paymentIntent;
    }

    public PaymentIntent get(String id) throws StripeException {
        Stripe.apiKey = secretKey;

        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);

        log.info(paymentIntent.toJson());
        return paymentIntent;
    }
}
