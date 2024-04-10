package mx.edu.utex.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utex.jwt.model.Role;
import mx.edu.utex.jwt.model.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UserDTO {
    String username;
    String password;
    String role;

    public User getUser(Role role) {
        return new User(
                this.username,
                this.password,
                role
        );
    }
}
