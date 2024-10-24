package dat.security.controllers;

import dat.enums.Role;
import dat.dtos.UserDTO;
import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.security.RouteRole;

import java.util.Set;


// to be used in the routes to check if the user is authenticated and has the necessary roles

public class AccessController {

    SecurityController securityController = SecurityController.getInstance();

    // this method is called before the route handler to check if the user is authenticated and has the necessary roles
    public void accessHandler(Context ctx) {

        // If no roles are specified on the endpoint, then anyone can access the route
        if (ctx.routeRoles().isEmpty() || ctx.routeRoles().contains(Role.REGULAR)){
           return;
        }

        // Check if the user is authenticated
        try {
            securityController.authenticate().handle(ctx);
        } catch (UnauthorizedResponse e) {
            throw new UnauthorizedResponse(e.getMessage());
        } catch (Exception e) {
            throw new UnauthorizedResponse("You need to log in, dude! Or you token is invalid.");
        }

        // Check if the user has the necessary roles to access the route
        UserDTO user = ctx.attribute("user");
        Set<RouteRole> allowedRoles = ctx.routeRoles(); // roles allowed for the current route
        if (!securityController.authorize(user, allowedRoles)) {
            throw new UnauthorizedResponse("Unauthorized with roles: " + user.getRoles() + ". Needed roles are: " + allowedRoles);
        }
    }
}
