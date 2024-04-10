package mx.edu.utex.jwt.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mx.edu.utex.jwt.model.User;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
public class UserList {
    private Set<User> users;
}
