//package dat.routes;
//
//import dat.controllers.impl.HotelController;
//import dat.security.enums.Role;
//import io.javalin.apibuilder.EndpointGroup;
//
//public class RecipeRoutes {
//
//    private final HotelController hotelController = new HotelController();
//
//    protected EndpointGroup getRoutes() {
//
//        return () -> {
//            post("/", hotelController::create, Role.USER);
//            get("/", hotelController::readAll);
//            get("/{id}", hotelController::read);
//            put("/{id}", hotelController::update);
//            delete("/{id}", hotelController::delete);
//        };
//    }
//}
