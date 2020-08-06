package com.augusto.stripe.payment;

import com.augusto.stripe.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;

    private String stripeId;

    @ManyToOne
    private User user;

    private String type = "card";
    private String number;
    private String cvc;
    private Long exp_month;
    private Long exp_year;


}
