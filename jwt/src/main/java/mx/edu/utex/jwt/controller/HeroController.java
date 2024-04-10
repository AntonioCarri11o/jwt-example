package mx.edu.utex.jwt.controller;

import mx.edu.utex.jwt.config.VariableGlobal;
import mx.edu.utex.jwt.model.Hero;
import mx.edu.utex.jwt.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hero")
@CrossOrigin(origins = VariableGlobal.CROSS_ORIGINS_URL)
public class HeroController {
    @Autowired
    HeroService service;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ROLE_COMMON')")
    public List<Hero> list() {
        return service.getAll();
    }
}
