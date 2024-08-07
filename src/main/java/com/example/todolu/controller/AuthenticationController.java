package com.example.todolu.controller;

import com.example.todolu.domain.user.User;
import com.example.todolu.infra.security.TokenJWTData;
import com.example.todolu.infra.security.TokenService;
import com.example.todolu.user.AuthenticationData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationData authenticationData) {
        try{
            var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationData.login(), authenticationData.password());
            var authentication =  manager.authenticate(authenticationToken);

            var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

            return ResponseEntity.ok(new TokenJWTData(tokenJWT));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
