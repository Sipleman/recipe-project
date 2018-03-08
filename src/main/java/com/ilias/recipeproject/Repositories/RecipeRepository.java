package com.ilias.recipeproject.Repositories;

import com.ilias.recipeproject.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
