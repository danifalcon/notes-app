package d.falcon.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Note extends PanacheEntity {

    @Column(nullable = false)
    public String title;

    @Column(nullable = false)
    public String message;

    @ManyToOne
    public NotesUser createdBy;

    public Note() {
    }

    public static List<Note> getAllNotes() {
        return Note.findAll().list();
    }
}
