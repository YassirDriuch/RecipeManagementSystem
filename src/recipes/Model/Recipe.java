package recipes.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Recipe {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    private String name, description;

    @NotNull
    @NotEmpty
    @ElementCollection
    private List<String> ingredients;

    @NotNull
    @NotEmpty
    @ElementCollection
    private List<String> directions;

    public Recipe(String name, String description, List<String> ingredients, List<String> directions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }
}
