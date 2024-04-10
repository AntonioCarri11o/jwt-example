package mx.edu.utex.jwt.dao;

import mx.edu.utex.jwt.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroDao extends JpaRepository<Hero, Long> {
}
