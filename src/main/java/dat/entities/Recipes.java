package dat.entities;

import dat.dtos.RecipeDTO;
import dat.enums.RecipeDifficulty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "recipes")
@Setter
@AllArgsConstructor
// Serialize the class. that means that the class can be converted to a sequence of bytes. so we ensure that the data is the same/correct on both end.

public class Recipes implements Serializable {


    // A serializable class must have a serial version UID
    @Serial
    private static final long serialVersionUID = 1L;

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

    @Enumerated (EnumType.STRING)
    @Column(name = "difficulty")
    private RecipeDifficulty difficulty;

    @ManyToOne
    @JoinColumn(name = "created_by")  // This is the foreign key
    private User createdBy;

    public Recipes(RecipeDTO recipeDTO){
        this.title = recipeDTO.getTitle();
        this.description = recipeDTO.getDescription();
        this.ingredientsAndGrams = recipeDTO.getIngredientsAndGrams();
        this.difficulty = recipeDTO.getDifficulty();
        this.createdBy = recipeDTO.getCreatedBy();
    }

    public Recipes(String title, String ingredientsAndGrams, String description, RecipeDifficulty difficulty, User user) {
        this.title = title;
        this.ingredientsAndGrams = ingredientsAndGrams;
        this.description = description;
        // .valueof checks if the string is equal to the enum value
        this.difficulty = difficulty;
        this.createdBy = user;
    }

    public Recipes(int id, String title, String ingredientsAndGrams, String description, String diffulculty) {
        this.id = id;
        this.title = title;
        this.ingredientsAndGrams = ingredientsAndGrams;
        this.description = description;
        this.difficulty = difficulty;
    }

    public Recipes(String title, String ingredientsAndGrams, String description, String diffulculty) {
        this.title = title;
        this.ingredientsAndGrams = ingredientsAndGrams;
        this.description = description;
        this.difficulty = difficulty;
    }


}// end class
