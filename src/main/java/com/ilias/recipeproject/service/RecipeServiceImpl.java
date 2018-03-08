package com.ilias.recipeproject.service;

import com.ilias.recipeproject.Repositories.CategoryRepository;
import com.ilias.recipeproject.Repositories.IngredientRepository;
import com.ilias.recipeproject.Repositories.RecipeRepository;
import com.ilias.recipeproject.Repositories.UnitOfMeasureRepository;
import com.ilias.recipeproject.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private CategoryRepository categoryRepository;


    public RecipeServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository,
                             IngredientRepository ingredientRepository, CategoryRepository categoryRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Recipe> getListOfRecipes() {
        List<Recipe> listOfRecipes = new ArrayList<>();
        recipeRepository.findAll().iterator().forEachRemaining(listOfRecipes::add);
        return listOfRecipes;
    }

    @Override
    public Recipe getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        return recipe;
    }
}
