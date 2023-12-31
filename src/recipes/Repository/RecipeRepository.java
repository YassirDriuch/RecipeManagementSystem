package recipes.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipes.Model.Recipe;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findRecipeById(Long id);
    Optional<List<Recipe>> findByCategoryIgnoreCaseOrderByDateDesc(String category);
    Optional<List<Recipe>> findByNameIgnoreCaseContainsOrderByDateDesc(String name);
}
