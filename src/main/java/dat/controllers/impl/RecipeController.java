package dat.controllers.impl;

import dat.config.HibernateConfig;
import dat.controllers.IController;
import dat.daos.impl.RecipeDAO;
import dat.dtos.RecipeDTO;
import dat.entities.Recipes;
import dat.exceptions.ApiException;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

public class RecipeController implements IController {

    RecipeDAO dao;

    public RecipeController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("recipes");
        this.dao = RecipeDAO.getInstance(emf);
    }

    //method to read a recipe by id. with path parameter to handle the GET request
    @Override
    public void read(Context ctx) throws ApiException {
        // Extracting the id from the path parameter
        int id = Integer.parseInt(ctx.pathParam("id"));
        // getting the recipeDTO from the dao
        RecipeDTO recipeDTO = dao.read(id);

        if (recipeDTO != null) {
            // setting the response status and body
            ctx.json(recipeDTO);
            // setting the response status 200 = the request has succeeded
            ctx.status(200);
        } else {
            // setting the response status 404 = the server has not found anything matching the Request-URI
            throw new ApiException(404, "No recipe found");
        }
    }

    // TODO implement the rest of the methods
    @Override
    public void readAll(Context ctx) {
        // getting all the recipes from the dao
        ctx.json(dao.readAll());
        //200 = the request has succeeded
        ctx.status(200);

    }

    @Override
    public void create(Context ctx) throws ApiException {
        if (ctx.body().isEmpty()) {
            throw new ApiException(400, "No recipe in request body");
        } else {
            // the ctx.bodyAsClass method is used to read the request json-body and convert(deserialize it) it to a RecipeDTO object
            RecipeDTO recipeDTO = ctx.bodyAsClass(RecipeDTO.class);
            // the dao.create method is used to create a new recipe in the database
            RecipeDTO newRecipe = dao.create(recipeDTO);
            // setting the response status and body
            ctx.json(newRecipe);
            ctx.status(201); // 201 = created
        }
    }

    @Override
    public void update(Context ctx) {
        RecipeDTO recipeDTO = ctx.bodyAsClass(RecipeDTO.class);
        int id = Integer.parseInt(ctx.pathParam("id"));
        RecipeDTO updatedDTO = dao.update(id, recipeDTO);

        ctx.json(updatedDTO);
        ctx.status(200); // 200 = OK

    }

    @Override
    public void delete(Context ctx) {
        // request
        int id = Integer.parseInt(ctx.pathParam("id"));
        // delete the recipe from id given. using our DAO
        dao.delete(id);
        // response
        ctx.status(204); // 204 = No Content

    }
}
