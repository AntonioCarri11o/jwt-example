package mx.edu.utex.jwt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "user")
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private Long id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "nonLocked")
    private boolean nonLocked;
    @JoinColumn(name = "fk_role", nullable = false)
    @ManyToOne
    private Role role;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.nonLocked = true;
        this.role = role;
    }



}
