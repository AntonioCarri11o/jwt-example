package mx.edu.utex.jwt.config;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import mx.edu.utex.jwt.dao.HeroDao;
import mx.edu.utex.jwt.dao.RoleDao;
import mx.edu.utex.jwt.dao.UserDao;
import mx.edu.utex.jwt.model.Hero;
import mx.edu.utex.jwt.model.Role;
import mx.edu.utex.jwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Bootstrap {

    @Autowired
    RoleDao roleDao;
    @Autowired
    UserDao userDao;
    @Autowired
    HeroDao heroDao;
    @Autowired
    PasswordEncoder encoder;

    @PostConstruct
    public void boot() {
        createRoles();
        createUsers();
        createHeroes();
    }

    private void createRoles() {
        roleDao.save(new Role("ROLE_ADMIN"));
        roleDao.save(new Role("ROLE_COMMON"));
    }
    private void createUsers() throws EntityNotFoundException {
        Optional<Role> roleAdmin = roleDao.findRoleByName("ROLE_ADMIN");
        Optional<Role> roleCommon = roleDao.findRoleByName("ROLE_COMMON");

        if (roleAdmin.isEmpty() || roleCommon.isEmpty()) {
            throw new EntityNotFoundException("Roles not found");
        }

        userDao.save(new User("admin", encoder.encode("pass"), roleAdmin.get()));
        userDao.save(new User("common", encoder.encode("pass"), roleCommon.get()));
    }

    private void createHeroes() {
        heroDao.save(new Hero("Superman", "Fly, super strenght, laser vision, indestructible", "https://wallpapercave.com/wp/wp12718196.jpg"));
        heroDao.save(new Hero("Batmam", "Rich", "https://wallpapercave.com/wp/wp12929402.jpg"));
        heroDao.save(new Hero("Green lantern", "Green ring", "https://wallpapercave.com/wp/wp8703344.jpg"));
    }
}
