package mx.edu.utex.jwt.service;

import jakarta.persistence.EntityNotFoundException;
import mx.edu.utex.jwt.dao.RoleDao;
import mx.edu.utex.jwt.dao.UserDao;
import mx.edu.utex.jwt.dto.UserDTO;
import mx.edu.utex.jwt.model.Role;
import mx.edu.utex.jwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    PasswordEncoder encoder;

    public void saveUser(UserDTO userDTO) throws EntityNotFoundException {
        Optional<Role> role = roleDao.findRoleByName(userDTO.getRole());
        if (role.isEmpty()) {
            throw new EntityNotFoundException("Rol incorrecto");
        }
        try {
            User user = userDTO.getUser(role.get());
            user.setPassword(encoder.encode(user.getPassword()));
            userDao.save(user);
        } catch (Exception e) {
            System.out.println("E: " + e.getMessage());
        }

    }
    public List<User> getAll() {
        return userDao.findAll();
    }
}
