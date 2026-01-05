package com.optica.manager.web.resources;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optica.manager.domain.entities.User;
import com.optica.manager.domain.repositories.UserRepository;
import com.optica.manager.dto.request.LoginRequest;
import com.optica.manager.dto.request.RegisterRequest;
import com.optica.manager.dto.response.LoginResponse;
import com.optica.manager.infra.security.TokenService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest body) {
        User user = this.userRepository.findByEmail(body.email()).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
        
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponse(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest body) {

        Optional<User> user = this.userRepository.findByEmail(body.email());

        if(user.isEmpty()){
            User newUser = new User();
        
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            this.userRepository.save(newUser);

            
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new LoginResponse(newUser.getName(), token));
            
        }
        return ResponseEntity.badRequest().build();
    }
}
