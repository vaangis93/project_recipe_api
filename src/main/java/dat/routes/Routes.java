package dat.routes;

import dat.controllers.impl.RecipeController;
import dat.enums.Role;
import dat.security.routes.SecurityRoutes;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {


    private final SecurityRoutes securityRoutes = new SecurityRoutes();
    private final RecipeRoutes recipeRoutes = new RecipeRoutes();

    public EndpointGroup getRoutes() {
        return () -> {
                path("/recipes", recipeRoutes.getRoutes());
        };
    }
}
