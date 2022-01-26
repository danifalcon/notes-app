package d.falcon.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class NotesUser extends PanacheEntity {

    public String name;

    public String surname;

    public Integer phone;

}
