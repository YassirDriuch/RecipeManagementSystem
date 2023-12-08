package recipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipesApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecipesApplication.class, args);
    }
}

echo "# RecipeManagementSystem" >> README.md
        git init
        git add *
        git commit -m "first commit"
        git branch -M main
        git remote add origin https://github.com/YassirDriuch/RecipeManagementSystem.git
        git push -u origin main