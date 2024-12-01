package dat.routes;

import dat.controllers.impl.RecipeController;
import dat.enums.Role;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class RecipeRoutes {

    private final RecipeController recipeController = new RecipeController();

    protected EndpointGroup getRoutes() {


        return () -> {

                post("/", recipeController::create, Role.ADMIN);
                put("/{id}", recipeController::update, Role.ADMIN);
                delete("/{id}", recipeController::delete, Role.ADMIN);
                get("/", recipeController::readAll, Role.ADMIN);// every1 can read all recipes
                get("/{id}", recipeController::read); // every1 can read a recipe
                get("/populate", recipeController::populate);


        };



    }
}
