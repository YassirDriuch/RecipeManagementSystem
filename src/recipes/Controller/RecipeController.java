package recipes.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipes.DTO.RecipeDTO;
import recipes.ExceptionHandler.NotFoundException;
import recipes.Model.Recipe;
import recipes.Repository.RecipeRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Map;

@RestController
public class RecipeController {

    private static Logger log = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    RecipeRepository recipeRepository;

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id) {
        Recipe recipe = recipeRepository.findRecipeById(id).orElseThrow(() -> new NotFoundException("Recipe not found!"));
        deleteRecipeFromRepo(recipe);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable Long id) {
        Recipe recipe = recipeRepository.findRecipeById(id).orElseThrow(() -> new NotFoundException("Recipe not found!"));
        return new ResponseEntity<>(new RecipeDTO(
                recipe.getName(),
                recipe.getDescription(),
                recipe.getIngredients(),
                recipe.getDirections()
        ), HttpStatus.OK);
    }

    @PostMapping("/api/recipe/new")
    public ResponseEntity<Map<String, Long>> saveRecipe(@RequestBody @Valid Recipe recipe) {
        log.info(recipe.toString());
        recipeRepository.save(recipe);
        return new ResponseEntity<>(Map.of("id", recipe.getId()), HttpStatus.OK);
    }

    @Transactional
    public void deleteRecipeFromRepo(Recipe recipe){
        recipeRepository.delete(recipe);
    }
}
