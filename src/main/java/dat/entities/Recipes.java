package dat.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "recipes")
@Setter
@AllArgsConstructor
public class Recipes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;


    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "ingedients_and_grams")
    private String ingredientsAndGrams;

    @Column(name = "description")
    private String description;

    @Column (name = "diffulculty")
    private String diffulculty;

    @Column (name = "created_by")
    @ManyToOne
    private User user;

    public Recipes(String title, String ingredientsAndGrams, String description, String diffulculty, User user) {
        this.title = title;
        this.ingredientsAndGrams = ingredientsAndGrams;
        this.description = description;
        this.diffulculty = diffulculty;
        this.user = user;
    }

    public Recipes(int id, String title, String ingredientsAndGrams, String description, String diffulculty) {
        this.id = id;
        this.title = title;
        this.ingredientsAndGrams = ingredientsAndGrams;
        this.description = description;
        this.diffulculty = diffulculty;
    }
}// end class
