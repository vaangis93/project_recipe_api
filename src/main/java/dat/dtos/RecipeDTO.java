package dat.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import dat.entities.Recipes;
import dat.entities.User;
import dat.enums.RecipeDifficulty;
import lombok.*;

import java.util.Objects;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class RecipeDTO {

    private String title;
//    @JsonProperty("ingredients_and_grams")
    private String ingredientsAndGrams;
    private String description;
    private RecipeDifficulty difficulty;
    private User createdBy;

    public RecipeDTO(Recipes recipes) {
        this.title = recipes.getTitle();
        this.ingredientsAndGrams = recipes.getIngredientsAndGrams();
        this.description = recipes.getDescription();
        this.difficulty = recipes.getDifficulty();
        this.createdBy = recipes.getCreatedBy();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeDTO recipeDTO = (RecipeDTO) o;
        return Objects.equals(title, recipeDTO.title) && Objects.equals(ingredientsAndGrams, recipeDTO.ingredientsAndGrams) && Objects.equals(description, recipeDTO.description) && difficulty == recipeDTO.difficulty && Objects.equals(createdBy, recipeDTO.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, ingredientsAndGrams, description, difficulty, createdBy);
    }
}
