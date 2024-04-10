package mx.edu.utex.jwt.controller;

import jakarta.persistence.EntityNotFoundException;
import mx.edu.utex.jwt.dto.UserDTO;
import mx.edu.utex.jwt.model.User;
import mx.edu.utex.jwt.response.Response;
import mx.edu.utex.jwt.response.UserList;
import mx.edu.utex.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import mx.edu.utex.jwt.config.VariableGlobal;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = VariableGlobal.CROSS_ORIGINS_URL)
public class UserController {
    @Autowired
    UserService service;
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody UserDTO userDTO) {
        try {
            service.saveUser(userDTO);
            return ResponseEntity.ok(new Response("Registro exitoso!", false));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(new Response(e.getMessage(), true));
        }
    }
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> list() {
        Set<User> users =new HashSet<>(service.getAll());
        System.out.println("trying");
        return service.getAll();
    }
}
