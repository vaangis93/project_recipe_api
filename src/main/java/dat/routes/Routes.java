package dat.routes;

import dat.security.routes.SecurityRoutes;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {


    private final SecurityRoutes securityRoutes = new SecurityRoutes();

    public EndpointGroup getRoutes() {
        return () -> {

                path("/auth", securityRoutes.getSecurityRoutes());
                path ("/protected", securityRoutes.getSecuredRoutes());
        };
    }
}
