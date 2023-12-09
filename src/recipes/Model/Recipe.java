package recipes.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Recipe {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name, description;

    @ElementCollection
    private List<String> ingredients;

    @ElementCollection
    private List<String> directions;

    public Recipe(String name, String description, List<String> ingredients, List<String> directions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }
}
