package mx.edu.utex.jwt.service;

import mx.edu.utex.jwt.dao.HeroDao;
import mx.edu.utex.jwt.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {
    @Autowired
    private HeroDao dao;

    public List<Hero> getAll() {
        return dao.findAll();
    }
}
