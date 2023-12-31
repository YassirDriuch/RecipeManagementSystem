package recipes.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import recipes.ExceptionHandler.BadRequestException;
import recipes.ExceptionHandler.ForbiddenException;
import recipes.ExceptionHandler.NotFoundException;
import recipes.Model.Recipe;
import recipes.Repository.RecipeRepository;
import recipes.Repository.UserRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class RecipeController {


    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        Recipe recipe = recipeRepository.findRecipeById(id).orElseThrow(() -> new NotFoundException("Recipe not found!"));
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<String> deleteRecipe(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
        Recipe recipe = recipeRepository.findRecipeById(id).orElseThrow(() -> new NotFoundException("Recipe not found!"));
        if (!(Objects.equals(userRepository.findByEmailIgnoreCase(userDetails.getUsername()).orElseThrow(() -> new NotFoundException("User not found!")).getId(), recipe.getAuthorId()))) throw new ForbiddenException("Cannot delete non-owned recipes!");
        deleteRecipeFromRepo(recipe);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/api/recipe/new")
    public ResponseEntity<Map<String, Long>> saveRecipe(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid Recipe recipe) {
        recipe.setAuthorId(userRepository.findByEmailIgnoreCase(userDetails.getUsername()).orElseThrow(() -> new NotFoundException("User not found!")).getId());
        recipeRepository.save(recipe);
        return new ResponseEntity<>(Map.of("id", recipe.getId()), HttpStatus.OK);
    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<String> updateRecipe(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id, @RequestBody @Valid Recipe recipe){
        Recipe toUpdate = recipeRepository.findRecipeById(id).orElseThrow(() -> new NotFoundException("Recipe not found!"));
        if (!(Objects.equals(userRepository.findByEmailIgnoreCase(userDetails.getUsername()).orElseThrow(() -> new NotFoundException("User not found!")).getId(), toUpdate.getAuthorId()))) throw new ForbiddenException("Cannot delete non-owned recipes!");
        toUpdate.setName(recipe.getName());
        toUpdate.setCategory(recipe.getCategory());
        toUpdate.setDescription(recipe.getDescription());
        toUpdate.setIngredients(recipe.getIngredients());
        toUpdate.setDirections(recipe.getDirections());
        recipeRepository.save(toUpdate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/recipe/search/")
    public ResponseEntity<List<Recipe>> getSearchResults(@RequestParam(required = false) String category, @RequestParam(required = false) String name) {
        if (name != null & category != null) throw new BadRequestException("Both values can't be requested at the same time!");
        if (name != null) {
            return new ResponseEntity<>(recipeRepository.findByNameIgnoreCaseContainsOrderByDateDesc(name).orElseThrow(() -> new NotFoundException("No recipes found!")), HttpStatus.OK);
        } else if (category != null) {
            return new ResponseEntity<>(recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category).orElseThrow(() -> new NotFoundException("No recipes found!")), HttpStatus.OK);
        } else throw new BadRequestException("Both values are empty!");
    }


    @Transactional
    public void deleteRecipeFromRepo(Recipe recipe){
        recipeRepository.delete(recipe);
    }
}
