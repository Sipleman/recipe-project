package com.ilias.recipeproject.controllers;

import com.ilias.recipeproject.Repositories.CategoryRepository;
import com.ilias.recipeproject.Repositories.UnitOfMeasureRepository;
import com.ilias.recipeproject.model.Category;
import com.ilias.recipeproject.model.Recipe;
import com.ilias.recipeproject.model.UnitOfMeasure;
import com.ilias.recipeproject.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {

    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
        List<Recipe> listOfRecipes = recipeService.getListOfRecipes();
        listOfRecipes.forEach(r->r.setUrl("/" + r.getId()));
        model.addAttribute("recipes", listOfRecipes);
        return "index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getRecipe(@PathVariable("id") long id, Model model){
        Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        return "recipe";
    }
}
