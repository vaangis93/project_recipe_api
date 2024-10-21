package dat.routes;

import io.javalin.apibuilder.EndpointGroup;

public class HotelRoute {

    private final HotelController hotelController = new HotelController();

    protected EndpointGroup getRoutes() {

        return () -> {
            post("/", hotelController::create);
            get("/", hotelController::readAll);
            get("/{id}", hotelController::read);
            put("/{id}", hotelController::update);
            delete("/{id}", hotelController::delete);
        };
    }
}
