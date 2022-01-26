package d.falcon.api;

import d.falcon.model.Note;
import d.falcon.model.NotesUser;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static d.falcon.model.NotesUser.Role.USER;

@Path("/notes")
public class NotesResource {

    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public List<Note> getAllNote() {
        return Note.getAllNotes();
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public Note createNote(Note newNote) {
        newNote.persist();
        return newNote;
    }

}