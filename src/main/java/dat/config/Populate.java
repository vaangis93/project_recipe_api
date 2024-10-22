package dat.config;


import dat.entities.Recipes;
import dat.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Populate {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("recipes");


        Set<Recipes> recipesSetForEmil = getRecipes1();
        Set<Recipes> recipesSetForJanus = getRecipes2();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User Emil = new User("user1", "user1");
        User Janus = new User("user1", "user1");
        Emil.setRecipes(recipesSetForEmil);
        Janus.setRecipes(recipesSetForJanus);
        em.persist(Emil);
        em.persist(Janus);
        em.getTransaction().commit();


    }


    private static Set<Recipes> getRecipes1() {

//        User user1 = new User("user1", "user1");
//        User user2 = new User("user1", "user1");
//        User user3 = new User("user1", "user1");
//        User user4 = new User("user1", "user1");
//        User user5 = new User("user1", "user1");
//        User user6 = new User("user1", "user1");

        Recipes recipes1 = new Recipes(1, "Browned Butter Caramel", "100 g cream. 100 g chocolate. 100 g browned butter. 80 g sugar", "Boil pasta in water", "Easy");
        Recipes recipes2 = new Recipes(2, "Pizza", "Pizza, 200g", "Bake pizza in oven", "Medium");
        Recipes recipes3 = new Recipes(3, "Burger", "Burger, 200g", "Fry burger in pan", "Hard");

        Set<Recipes> recipesSet = new HashSet<>();

        recipesSet.add(recipes1);
        recipesSet.add(recipes2);
        recipesSet.add(recipes3);


        return recipesSet;
    }

    private static Set<Recipes> getRecipes2() {
        Recipes recipes4 = new Recipes(4, "Salad", "Salad, 200g", "Mix salad in bowl", "Easy");
        Recipes recipes5 = new Recipes(5, "Soup", "Soup, 200g", "Boil soup in pot", "Medium");
        Recipes recipes6 = new Recipes(6, "Cake", "Cake, 200g", "Bake cake in oven", "Hard");

        Set<Recipes> recipesSet = new HashSet<>();

        recipesSet.add(recipes4);
        recipesSet.add(recipes5);
        recipesSet.add(recipes6);

        return recipesSet;
    }
}


//        Set<Room> calRooms = getCalRooms();
//        Set<Room> hilRooms = getHilRooms();
//
//        try (var em = emf.createEntityManager()) {
//            em.getTransaction().begin();
//            Hotel california = new Hotel("Hotel California", "California", Hotel.HotelType.LUXURY);
//            Hotel hilton = new Hotel("Hilton", "Copenhagen", Hotel.HotelType.STANDARD);
//            california.setRooms(calRooms);
//            hilton.setRooms(hilRooms);
//            em.persist(california);
//            em.persist(hilton);
//            em.getTransaction().commit();
//        }
//    }
//
//    @NotNull
//    private static Set<Room> getCalRooms() {
//        Room r100 = new Room(100, new BigDecimal(2520), Room.RoomType.SINGLE);
//        Room r101 = new Room(101, new BigDecimal(2520), Room.RoomType.SINGLE);
//        Room r102 = new Room(102, new BigDecimal(2520), Room.RoomType.SINGLE);
//        Room r103 = new Room(103, new BigDecimal(2520), Room.RoomType.SINGLE);
//        Room r104 = new Room(104, new BigDecimal(3200), Room.RoomType.DOUBLE);
//        Room r105 = new Room(105, new BigDecimal(4500), Room.RoomType.SUITE);
//
//        Room[] roomArray = {r100, r101, r102, r103, r104, r105};
//        return Set.of(roomArray);
//    }
//
//    @NotNull
//    private static Set<Room> getHilRooms() {
//        Room r111 = new Room(111, new BigDecimal(2520), Room.RoomType.SINGLE);
//        Room r112 = new Room(112, new BigDecimal(2520), Room.RoomType.SINGLE);
//        Room r113 = new Room(113, new BigDecimal(2520), Room.RoomType.SINGLE);
//        Room r114 = new Room(114, new BigDecimal(2520), Room.RoomType.DOUBLE);
//        Room r115 = new Room(115, new BigDecimal(3200), Room.RoomType.DOUBLE);
//        Room r116 = new Room(116, new BigDecimal(4500), Room.RoomType.SUITE);
//
//        Room[] roomArray = {r111, r112, r113, r114, r115, r116};
//        return Set.of(roomArray);
//    }



