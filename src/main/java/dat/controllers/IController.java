package dat.controllers;

import dat.exceptions.ApiException;
import io.javalin.http.Context;

public interface IController{
    void read(Context ctx) throws ApiException;
    void readAll(Context ctx);
    void create(Context ctx) throws ApiException;
    void update(Context ctx);
    void delete(Context ctx);

}
