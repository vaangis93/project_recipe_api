package dat.entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "username", length = 25)
    private String username;

    @Column(name = "password", length = 40)
    private String password;

    // fetch = FetchType.EAGER is used to load all the data from the database at once. so if we get the "father" the child will be loaded as well
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Recipes> recipes = new HashSet<>();

    @ManyToMany
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}


