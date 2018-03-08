package com.ilias.recipeproject.bootstrap;

import com.ilias.recipeproject.Repositories.CategoryRepository;
import com.ilias.recipeproject.Repositories.IngredientRepository;
import com.ilias.recipeproject.Repositories.RecipeRepository;
import com.ilias.recipeproject.Repositories.UnitOfMeasureRepository;
import com.ilias.recipeproject.model.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private CategoryRepository categoryRepository;

    public DevBootstrap(UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository,
                        IngredientRepository ingredientRepository, CategoryRepository categoryRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }
    private List<Recipe> getRecipes(){

    List<Recipe> recipes = new ArrayList<>();
    Optional<UnitOfMeasure> teaSpoon = unitOfMeasureRepository.findByDescription("Teaspoon");
    Optional<UnitOfMeasure> tableSpoon = unitOfMeasureRepository.findByDescription("Tablespoon");
    Optional<UnitOfMeasure> cup = unitOfMeasureRepository.findByDescription("Cup");
    Optional<UnitOfMeasure> pinch = unitOfMeasureRepository.findByDescription("Pinch");
    if(!(teaSpoon.isPresent() && tableSpoon.isPresent() && cup.isPresent() && pinch.isPresent())){
        throw new RuntimeException();
    }

    UnitOfMeasure tS = teaSpoon.get();
    UnitOfMeasure tableS = tableSpoon.get();
    UnitOfMeasure cupU = cup.get();
    UnitOfMeasure pinchU = pinch.get();

    Optional<Category> category1 = categoryRepository.findByDescription("American");
    Optional<Category> category2 = categoryRepository.findByDescription("Mexican");

    if(!(category1.isPresent() && category2.isPresent())){
        throw new RuntimeException();
    }

    Category american = category1.get();
    Category mexican = category2.get();
    Recipe recipe1 = new Recipe();
    Notes notes = new Notes();

    notes.setRecipeNotes("Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).");
    notes.setRecipe(recipe1);
    recipe1.setNotes(notes);

    recipe1.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
            "\n" +
            "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
            "\n" +
            "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
            "\n" +
            "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
            "\n" +
            "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");


    recipe1.addIngredient(new Ingredient("Avocado", 2.0, cupU));
    recipe1.addIngredient(new Ingredient("Lime Juice", 1.0, tableS));
    recipe1.addIngredient(new Ingredient("Red Onion", 2.0, tableS));
    recipe1.addIngredient(new Ingredient("Cilantro", 2.0, tableS));

    recipe1.getCategories().add(american);
    recipe1.getCategories().add(mexican);
    recipe1.setDescription("Guacamole");
    recipe1.setPrepTime(20);
    recipe1.setDifficulty(Difficulty.MEDIUM);
    recipe1.setCookTime(20);
    recipes.add(recipe1);

    Recipe recipe2 = new Recipe();
    Notes notes2 = new Notes();
    notes2.setRecipeNotes("This method makes delicious, buttery, cheesey tacos. If you are avoiding butter, or frying oil (which you could also use), you could make these in a microwave. Soften the tortillas first in the microwave. We use 20 seconds on high per tortilla, with the tortillas sitting on a paper towel in the microwave to absorb moisture. Once they've been softened this way you can add cheese and fold them over and heat them a few seconds more, just until the cheese melts.");
    notes2.setRecipe(recipe2);

    recipe2.setDescription("Tacos");
    recipe2.setCookTime(20);
    recipe2.setDifficulty(Difficulty.MEDIUM);
    recipe2.setPrepTime(10);
    recipe2.setNotes(notes2);
    recipe2.setServing(5);
    recipe2.setDirections("1 Heat up the cast iron frying pan on medium high heat. While the pan is heating, prepare your ingredients. Cut some slices of cheese. Get the salsa out of the fridge. Thinly slice as many slices of apple as you want tacos. (3 tacos, 3 apple slices). Cut, remove seed, slice, and remove from skin the avocado slices you want. Slice some lettuce and any leftover meat you want to add in.2 When the pan is hot (sprinkle a few drops of water on it, they should sizzle) lightly butter one side of one tortilla and place it in the pan, butter side down. Using a spatula, quickly flip the tortilla over to the other side, and back again, repeating until there is melted butter on both sides of the tortilla. Let the tortilla sit in hot pan until pockets of air start to form bubbles in the tortilla.3 Add slices of cheese to one side of the tortilla. Make sure to allow room for the cheese to melt and not spread all over the pan.\n" +
            "\n" +
            " 4 Flip the side of tortilla without the cheese over the side with the cheese, as if you were making an omelette. Move the folded cheese taco to one side of the pan. Add a second tortilla, lightly buttered on one side, and repeat the process. You can tell when the taco is done by pressing down on it with a spoon or fork or edge of the spatula. If it gives easily, the cheese is melted and you can remove the taco from the pan");

    recipe2.getCategories().add(mexican);
    recipe2.addIngredient(new Ingredient("Tortilas", 2.0, cupU));
    recipe2.addIngredient(new Ingredient("Butter", 100.0, pinchU));
    recipe2.addIngredient(new Ingredient("Cheddar", 100.0, pinchU));
    recipe2.addIngredient(new Ingredient("Salsa", 1.0, cupU));

    recipes.add(recipe2);
    return recipes;
}
}
