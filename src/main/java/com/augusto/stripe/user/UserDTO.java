package com.augusto.stripe.user;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String stripeId;
    private String email;
    private String name;
    private String description;
    private String phone;
}
