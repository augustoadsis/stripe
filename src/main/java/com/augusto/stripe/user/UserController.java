package com.augusto.stripe.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UserDTO user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
