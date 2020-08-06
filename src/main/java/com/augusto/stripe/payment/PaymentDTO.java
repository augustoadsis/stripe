package com.augusto.stripe.payment;

import com.augusto.stripe.user.UserDTO;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Long id;
    private String stripeId;
    private UserDTO user;
    private String type = "card";
    private String number;
    private String cvc;
    private Long exp_month;
    private Long exp_year;
}
