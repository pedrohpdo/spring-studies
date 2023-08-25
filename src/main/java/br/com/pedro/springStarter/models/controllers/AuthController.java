package br.com.pedro.springStarter.models.controllers;

import br.com.pedro.springStarter.models.entities.AuthenticationDataDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/login")
public class AuthenticationController {
    @Autowired
    AuthenticationManager manager;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationDataDTO data) {
        var token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = manager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
