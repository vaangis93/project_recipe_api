package dat.entities;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "roles")
@ToString
@Getter
@Setter
// Serialize the class. that means that the class can be converted to a sequence of bytes
public class Role implements Serializable {

    // A serializable class must have a serial version UID
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "password", length = 40)
    private String password;

}
