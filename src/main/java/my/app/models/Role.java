package my.app.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    String name;

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {

    }

    @Override
    public String getAuthority() {
        return name;
    }

    public void setAuthority(String name) {
        this.name = name;
    }

    //    USER(Set.of(Permission.USER_READ)),
//    ADMIN(Set.of(Permission.USER_READ, Permission.USER_WRITE));

//    private final Set<Permission> permissions;
//
//    Role(Set<Permission> permissions) {
//        this.permissions = permissions;
//    }
//
//    public Set<Permission> getPermissions() {
//        return permissions;
//    }
//
//    public Set<SimpleGrantedAuthority> getAuthorities() {
//        return getPermissions().stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toSet());
//    }
}


