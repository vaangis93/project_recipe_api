package dat.security.daos;

import dat.entities.User;
import dat.security.exceptions.ValidationException;
import dat.dtos.UserDTO;

public interface ISecurityDAO {
    UserDTO getVerifiedUser(UserDTO userDTO) throws ValidationException;
    User createUser(UserDTO userDTO);
    User addRole(UserDTO user, String newRole);
}
