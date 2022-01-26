package d.falcon.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import java.util.Optional;

@Entity
public class NotesUser extends PanacheEntity {

    @Column(unique = true)
    public String username;

    public String password;

    @Email
    @Column(unique = true)
    public String email;

    @Enumerated(EnumType.STRING)
    public Role role;

    public enum Role {
        ADMIN,
        USER
    }

    public static Optional<NotesUser> findByUsername(String username) {
        return find("username", username).singleResultOptional();
    }

}
