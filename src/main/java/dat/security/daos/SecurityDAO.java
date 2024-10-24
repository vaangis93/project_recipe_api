package dat.security.daos;


import dat.entities.Role;
import dat.entities.User;
import dat.security.exceptions.ApiException;
import dat.security.exceptions.ValidationException;
import dat.dtos.UserDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;


// to handle security in the API
public class SecurityDAO implements ISecurityDAO {

    private static ISecurityDAO instance;
    private static EntityManagerFactory emf;

    public SecurityDAO(EntityManagerFactory _emf) {
        emf = _emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public UserDTO getVerifiedUser(UserDTO userDTO) throws ValidationException {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :name", User.class);
            query.setParameter("name", userDTO.getUsername());
            User user = query.getSingleResult();
//            User user = em.find(User.class, username);
            if (user == null)
                throw new EntityNotFoundException("No user found with username: " + user); //RuntimeException
            user.getRoles().size(); // force roles to be fetched from db
            if (!user.verifyPassword(userDTO.getPassword()))
                throw new ValidationException("Wrong password");
            return new UserDTO(user.getUsername(), user.getRoles().stream().map(r -> r.getRoleName()).collect(Collectors.toSet()));
        }
    }

    @Override
    public User createUser(UserDTO userDTO) {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :name", User.class);
            query.setParameter("name", userDTO.getUsername());

            // Use getResultList to avoid NoResultException
            List<User> users = query.getResultList();
            if (!users.isEmpty()) {
                throw new EntityExistsException("User with username: " + userDTO.getUsername() + " already exists");
            }

            User userEntity = new User(userDTO.getUsername(), userDTO.getPassword());
            em.getTransaction().begin();

            Role userRole = em.find(Role.class, "user");
            if (userRole == null) {
                userRole = new Role("user");
                em.persist(userRole);
            }

            userEntity.addRole(userRole);
            em.persist(userEntity);
            em.getTransaction().commit();
            return userEntity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException(400, e.getMessage());
        }
    }


// ------------------ the first create method made --------------------
//    public User createUser(UserDTO userDTO) {
//        try (EntityManager em = getEntityManager()) {
////            User userEntity = em.find(User.class, userDTO.getUsername());
//            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :name", User.class);
//            query.setParameter("name", userDTO.getUsername());
//            User userEntity = query.getSingleResult();
//            if (userEntity != null)
//                throw new EntityExistsException("User with username: " + userDTO.getUsername() + " already exists");
//            userEntity = new User(userDTO.getUsername(), userDTO.getPassword());
//            em.getTransaction().begin();
//            Role userRole = em.find(Role.class, "user");
//            if (userRole == null)
//                userRole = new Role("user");
//            em.persist(userRole);
//            userEntity.addRole(userRole);
//            em.persist(userEntity);
//            em.getTransaction().commit();
//            return userEntity;
//        }catch (Exception e){
//            e.printStackTrace();
//            throw new ApiException(400, e.getMessage());
//        }
//    }

    @Override
       public User addRole(UserDTO userDTO, String newRole) {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :name", User.class);
            query.setParameter("name", userDTO.getUsername());
            User user = query.getSingleResult();
            if (user == null)
                throw new EntityNotFoundException("No user found with username: " + userDTO.getUsername());
            em.getTransaction().begin();
                Role role = em.find(Role.class, newRole);
                if (role == null) {
                    role = new Role(newRole);
                    em.persist(role);
                }
                user.addRole(role);
                //em.merge(user);
            em.getTransaction().commit();
            return user;
        }
    }
}

