package com.augusto.stripe.payment.method;

import com.augusto.stripe.payment.Payment;
import com.augusto.stripe.payment.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentMethod;
import com.stripe.param.PaymentMethodAttachParams;
import com.stripe.param.PaymentMethodCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentMethodService {

    @Value("${stripe.secretKey}")
    private String secretKey;

    @Autowired
    @Lazy
    PaymentService paymentService;

    public PaymentMethod create(Long id) throws StripeException {

        Payment payment = paymentService.findById(id);

        Stripe.apiKey = secretKey;

        PaymentMethodCreateParams.CardDetails card =
        PaymentMethodCreateParams.CardDetails.builder()
                    .setNumber(payment.getNumber())
                    .setCvc(payment.getCvc())
                    .setExpMonth(payment.getExp_month())
                    .setExpYear(payment.getExp_year())
                    .build();

        PaymentMethodCreateParams params =
                PaymentMethodCreateParams.builder()
                        .setType(PaymentMethodCreateParams.Type.CARD)
                        .setCard(card)
                        .build();

        PaymentMethod paymentMethod = PaymentMethod.create(params);
        log.info(paymentMethod.toJson());

        PaymentMethodAttachParams attachParams =
                PaymentMethodAttachParams.builder()
                    .setCustomer(payment.getUser().getStripeId())
                    .build();
        paymentMethod = paymentMethod.attach(attachParams);
        log.info(paymentMethod.toJson());

        payment.setStripeId(paymentMethod.getId());
        paymentService.update(payment);

        return paymentMethod;
    }

    public PaymentMethod get(String id) throws StripeException {
        Stripe.apiKey = secretKey;

        PaymentMethod paymentMethod = PaymentMethod.retrieve(id);

        log.info(paymentMethod.toJson());
        return paymentMethod;
    }
}
