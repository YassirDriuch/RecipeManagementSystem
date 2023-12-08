package recipes.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recipes.Model.Recipe;

@RestController
public class RecipeController {
    Recipe savedRecipe = new Recipe();

    @GetMapping("/api/recipe")
    public ResponseEntity<Recipe> getRecipe() {
        return new ResponseEntity<>(savedRecipe, HttpStatus.OK);
    }

    @PostMapping("/api/recipe")
    public ResponseEntity<Recipe> setRecipe(@RequestBody Recipe recipe) {
        savedRecipe = recipe;
        return new ResponseEntity<>(savedRecipe, HttpStatus.OK);
    }
}
