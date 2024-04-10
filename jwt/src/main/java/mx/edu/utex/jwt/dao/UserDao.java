package mx.edu.utex.jwt.dao;

import mx.edu.utex.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
