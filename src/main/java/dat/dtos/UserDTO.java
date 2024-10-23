//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package dat.dtos;

import dat.entities.Recipes;
import dat.entities.User;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class UserDTO {

    private String username;
    private String password;
    Set<Recipes> recipes = new HashSet();
    Set<String> roles = new HashSet();

    public UserDTO(User user) {
        this.username = user.getUserName();
        this.password = user.getPassword();
        this.roles = user.getRoles().stream().map(role -> role.getRoleName()).collect(Collectors.toSet());
        this.recipes = user.getRecipes();
    }

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDTO(String username, Set<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.username, this.roles});
    }


    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            UserDTO dto = (UserDTO) o;
            return Objects.equals(this.username, dto.username) && Objects.equals(this.roles, dto.roles);
        } else {
            return false;
        }
    }


}// end class
