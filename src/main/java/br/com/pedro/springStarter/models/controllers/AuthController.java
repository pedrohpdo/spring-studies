package br.com.pedro.springStarter.models.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedro.springStarter.infra.security.TokenServices;
import br.com.pedro.springStarter.models.entities.RequestTokenDTO;
import br.com.pedro.springStarter.models.entities.ResponseTokenDTO;
import br.com.pedro.springStarter.models.entities.User;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/api/login")
public class AuthController {
    
	@Autowired
    AuthenticationManager manager;
    
	@Autowired
	private TokenServices tokenService;
    
    @PostMapping
    public ResponseEntity<ResponseTokenDTO> login(@RequestBody @Valid RequestTokenDTO data) {
        var token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = manager.authenticate(token);
        
        String response = tokenService.generateToken((User) authentication.getPrincipal());
        
        return ResponseEntity.ok().body(new ResponseTokenDTO(response));
    } 
}
