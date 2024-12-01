package dat.controllers.impl;

import dat.config.ApplicationConfig;
import dat.config.HibernateConfig;
import dat.dtos.UserDTO;
import dat.entities.Recipes;
import dat.exceptions.ApiException;
import dat.security.controllers.SecurityController;
import dat.security.daos.SecurityDAO;
import dat.security.exceptions.ValidationException;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RecipeControllerTest {

    private final static RecipeController recipeController = new RecipeController();
    private final static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
    private final SecurityController securityController = SecurityController.getInstance();
    private final SecurityDAO securityDAO = new SecurityDAO(emf);
    private static Javalin app;
    private static Recipes[] recipesList;
    private static Recipes recipe1, recipe2, recipe3;
    private UserDTO userDTO, adminDTO;
    private String userToken, adminToken;
    private static final String BASE_URL = "http://localhost:7070/api";

    @BeforeAll
    void setUpAll() {
        HibernateConfig.setTest(true);

        // Start api
        app = ApplicationConfig.startServer(7070);
    }

    @BeforeEach
    void setUp() throws ValidationException, ApiException {
        // Populate the database with users with recipes
        System.out.println("Populating database with recipes");
        Recipes[] recipes = Populator.populateRecipes();
        recipe1 = recipesList[0];
        recipe2 = recipesList[1];
        recipe3 = recipesList[2];
        UserDTO[] userDTOS = Populator.populateUsersWithRoles();
        userDTO = userDTOS[0];
        adminDTO = userDTOS[1];

        try {
            UserDTO verifiedUser = securityDAO.getVerifiedUser(userDTO.getUsername(), userDTO.getPassword());
            userToken = securityController.createToken(verifiedUser);
            UserDTO verifiedAdmin = securityDAO.getVerifiedUser(adminDTO.getUsername(), adminDTO.getPassword());
            adminToken = securityController.createToken(verifiedAdmin);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void read() {
    }

    @Test
    void readAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}