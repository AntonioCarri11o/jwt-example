package mx.edu.utex.jwt.controller;

import mx.edu.utex.jwt.dto.LoginDTO;
import mx.edu.utex.jwt.response.TokenResponse;
import mx.edu.utex.jwt.service.auth.JwtService;
import mx.edu.utex.jwt.service.auth.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import mx.edu.utex.jwt.config.VariableGlobal;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = VariableGlobal.CROSS_ORIGINS_URL)
public class AuthController {
    @Autowired
    UserAuthService userAuthService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );
            System.out.println("Si lo intenta");
            if (authentication.isAuthenticated()) {
                TokenResponse response = new TokenResponse(false, jwtService.generateToken(loginDTO.getUsername()));
                return ResponseEntity.ok(response);
            } else {
                System.out.println("ni mais");
                throw new UsernameNotFoundException("Usuario invalido");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new UsernameNotFoundException("Usuario inválido");
        }
    }

}
