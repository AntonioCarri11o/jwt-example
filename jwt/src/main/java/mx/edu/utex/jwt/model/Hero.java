package mx.edu.utex.jwt.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "hero")
@Entity
public class Hero {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String descrition;
    @Column(name = "photo", nullable = false)
    private String url_image;

    public Hero(String name, String description, String url) {
        this.name = name;
        this.descrition = description;
        this.url_image = url;
    }
}
