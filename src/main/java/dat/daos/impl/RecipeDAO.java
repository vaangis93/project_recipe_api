package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.RecipeDTO;
import dat.entities.Recipes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class RecipeDAO implements IDAO<RecipeDTO, Integer> {

    private static RecipeDAO instance;
    private static EntityManagerFactory emf;

    public static RecipeDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RecipeDAO();
        }
        return instance;
    }

    @Override
    public RecipeDTO read(Integer integer) {
        try (EntityManager em = emf.createEntityManager()) {
            Recipes recipe = em.find(Recipes.class, integer);
            return new RecipeDTO(recipe);
        }
    }

    @Override
    public List<RecipeDTO> readAll() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<RecipeDTO> query = em.createQuery("SELECT r FROM Recipes r", RecipeDTO.class);
        return query.getResultList();
        }
    }

    @Override
    public RecipeDTO create(RecipeDTO recipeDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Recipes recipes = new Recipes(recipeDTO);
            em.persist(recipes);
            em.getTransaction().commit();
            return new RecipeDTO(recipes);
        }
    }

    @Override
    public RecipeDTO update(Integer integer, RecipeDTO recipeDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Recipes recipesBeforeChange = em.find(Recipes.class, integer);
            recipesBeforeChange.setTitle(recipeDTO.getTitle());
            recipesBeforeChange.setDescription(recipeDTO.getDescription());
            recipesBeforeChange.setIngredientsAndGrams(recipeDTO.getIngredientsAndGrams());
            Recipes recipesAfterChange = em.merge(recipesBeforeChange);
            em.getTransaction().commit();
            return new RecipeDTO(recipesAfterChange);

        }
    }

    @Override
    public void delete(Integer integer) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Recipes recipes = em.find(Recipes.class, integer);
            if (recipes != null) {
                em.remove(recipes);
            }
            em.getTransaction().commit();
        }
    }

}// end class
