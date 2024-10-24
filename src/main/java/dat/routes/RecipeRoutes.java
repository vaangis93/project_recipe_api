package dat.routes;

import dat.controllers.impl.RecipeController;
import dat.enums.Role;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class RecipeRoutes {

    private final RecipeController recipeController = new RecipeController();

    protected EndpointGroup getRoutes() {

            // TODO make sure that the routes are protected by the correct roles. for some reason it just ignorer the roles
        return () -> {

                post("/", recipeController::create, Role.ADMIN);
                put("/{id}", recipeController::update);
                delete("/{id}", recipeController::delete);
                get("/", recipeController::readAll);
                get("/{id}", recipeController::read);

//       --------------- make these work. atm they dont work. --------------
//            post("/", recipeController::create, Role.ADMIN);
//            put("/{id}", recipeController::update, Role.ADMIN);
//            delete("/{id}", recipeController::delete, Role.ADMIN);
//            get("/", recipeController::readAll, Role.REGULAR, Role.ADMIN);
//            get("/{id}", recipeController::read, Role.REGULAR, Role.ADMIN);
 //       -------------------------------------------------------------------
        };



    }
}
