package com.ilias.recipeproject.service;

import com.ilias.recipeproject.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RecipeService {
    List<Recipe> getListOfRecipes();
    Recipe getRecipeById(Long id);

}
