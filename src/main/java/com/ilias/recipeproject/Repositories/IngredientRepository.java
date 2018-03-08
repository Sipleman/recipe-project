package com.ilias.recipeproject.Repositories;

import com.ilias.recipeproject.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    Optional<Ingredient> findByDescription(String description);
}
