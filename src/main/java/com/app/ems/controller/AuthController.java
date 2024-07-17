package com.app.ems.controller;

import com.app.ems.dto.JWTAuthResponse;
import com.app.ems.dto.LoginDto;
import com.app.ems.dto.RegisterDto;
import com.app.ems.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ems/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Register the user REST API
    @PostMapping(value = {"/sign-in","/register"})
    public ResponseEntity<String> registerUser(
            @RequestBody RegisterDto registerDto){
        String register = authService.register(registerDto);
        return ResponseEntity.ok(register);
    }

    @PostMapping(value = {"/login","/sign-in"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setJwt(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }
}
