package dev.luanfernandes.app.controller;

import dev.luanfernandes.app.config.security.TokenService;
import dev.luanfernandes.app.domain.dto.TokenDto;
import dev.luanfernandes.app.domain.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm form){
        UsernamePasswordAuthenticationToken loginData = form.converter();
        try {
            Authentication authenticate = authenticationManager.authenticate(loginData);
            String token = tokenService.generateToken(authenticate);
            return ResponseEntity.ok(new TokenDto(token,"Bearer"));
        } catch (AuthenticationException e) {
           return ResponseEntity.badRequest().build();
        }
    }
}
