package dat.config;


import dat.entities.Recipes;
import dat.entities.Role;
import dat.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

import static dat.enums.RecipeDifficulty.*;

public class Populate {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("recipes");

        // setting the recipe-collection-Set method to a variable for emil and janus
        Set<Recipes> recipesSetForEmil = getRecipes1();
        Set<Recipes> recipesSetForJanus = getRecipes2();

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Recipes recipes1 = new Recipes("Browned Butter Caramel", "100 g cream. 100 g chocolate. 100 g browned butter. 80 g sugar", "Boil pasta in water", EASY);
            //creating users
            User emil = new User("emil", "user1");
            User janus = new User("janus", "user1");
            //creating roles
            Role regularRole = new Role("regular");
            Role adminRole = new Role("admin");
            // adding the roles to the users
            emil.addRole(adminRole);
            janus.addRole(regularRole);

            // Add all recipes(Set/collections) for the users and setting the user in the recipes
            janus.getRecipes().addAll(recipesSetForJanus); // Add all recipes to janus's set of recipes
            recipesSetForJanus.forEach(recipes -> recipes.setCreatedBy(janus)); // Set the user in the recipe to janus. for the entire Set/collections
            emil.getRecipes().addAll(recipesSetForEmil);
            recipesSetForEmil.forEach(recipes -> recipes.setCreatedBy(emil));

            // adding a single recipe to emil and setting the user in the recipe to emil
            emil.getRecipes().add(recipes1); // Add recipe to emil's set of recipes
            recipes1.setCreatedBy(emil); // Set the user in the recipe to emil.
            // no need for persisting the recipe, because it is already getting persisted when persisting user. because of the cascade type(PERSIST).

            // persist the users
            em.persist(emil);
            em.persist(janus);
            em.getTransaction().commit();

            // print the users to the console
            System.out.println(janus);
            System.out.println(emil);
        }

    }


    @NotNull
    private static Set<Recipes> getRecipes1() {

        Recipes recipes1 = new Recipes("Browned Butter Caramel", "100 g cream. 100 g chocolate. 100 g browned butter. 80 g sugar", "Boil pasta in water", EASY);
        Recipes recipes2 = new Recipes("Lemon Tart", "200 g flour. 100 g butter. 150 g sugar. 3 eggs. 2 lemons", "Make the tart crust with flour and butter, mix the filling with eggs, lemon juice, and sugar, bake until set.", VERY_EASY);
        Recipes recipes3 = new Recipes("Chocolate Mousse", "150 g dark chocolate. 3 eggs. 50 g sugar. 200 ml cream", "Melt chocolate, fold in whipped cream, and beaten egg whites, chill until set.", HARD);


        Set<Recipes> recipesSet = new HashSet<>();

        recipesSet.add(recipes1);
        recipesSet.add(recipes2);
        recipesSet.add(recipes3);


        return recipesSet;
    }

    @NotNull
    private static Set<Recipes> getRecipes2() {
        Recipes recipes4 = new Recipes("Vanilla Panna Cotta", "400 ml cream. 50 g sugar. 1 vanilla bean. 3 gelatine sheets", "Heat cream with vanilla and sugar, dissolve gelatine, pour into molds and chill until set.", EASY);
        Recipes recipes5 = new Recipes("Raspberry Cheesecake", "200 g cream cheese. 100 g sugar. 150 g raspberries. 150 g digestive biscuits. 50 g butter", "Crush biscuits and mix with melted butter for the base, blend cream cheese with sugar and fold in raspberries, pour over the base and chill.", MEDIUM);
        Recipes recipes6 = new Recipes("Tiramisu", "300 g mascarpone. 150 ml espresso. 100 g sugar. 200 g ladyfingers. 3 eggs. Cocoa powder", "Whisk egg yolks with sugar and fold in mascarpone, dip ladyfingers in espresso, layer them with the mascarpone mixture, and dust with cocoa.", HARD);

        Set<Recipes> recipesSet = new HashSet<>();

        recipesSet.add(recipes4);
        recipesSet.add(recipes5);
        recipesSet.add(recipes6);

        return recipesSet;
    }
}





