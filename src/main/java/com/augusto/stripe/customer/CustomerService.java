package com.augusto.stripe.customer;

import com.augusto.stripe.user.User;
import com.augusto.stripe.user.UserService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerService {

    @Value("${stripe.secretKey}")
    private String secretKey;

    @Autowired
    @Lazy
    UserService userService;

    public Customer create(Long id) throws StripeException {

        User user = userService.findById(id);

        Stripe.apiKey = secretKey;

        CustomerCreateParams params =
                CustomerCreateParams.builder()
                        .setName(user.getName())
                        .setEmail(user.getEmail())
                        .setDescription(user.getDescription())
                        .build();

        Customer customer = Customer.create(params);
        log.info(customer.toJson());

        user.setStripeId(customer.getId());
        userService.update(user);
        return customer;
    }

    public Customer get(String id) throws StripeException {
        Stripe.apiKey = secretKey;

        Customer customer = Customer.retrieve(id);

        log.info(customer.toJson());
        return customer;
    }
}
