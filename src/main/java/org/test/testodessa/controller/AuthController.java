package org.test.testodessa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.test.testodessa.model.DTO.LoginRequest;
import org.test.testodessa.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class AuthController {
    private final AuthService authService;

    @RequestMapping(value = "/authenticate" , method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.loginUser(loginRequest));
    }

    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginRequest registerUser){
        return ResponseEntity.ok(authService.registerUser(registerUser));
    }

}
