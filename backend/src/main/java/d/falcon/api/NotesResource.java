package d.falcon.api;

import d.falcon.model.Note;
import d.falcon.model.NotesUser;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/notes")
public class NotesResource {

    @Inject
    JsonWebToken jwt;

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
        newNote.createdBy = NotesUser.findByUsername(jwt.getName()).orElseThrow(InternalServerErrorException::new);
        newNote.persist();
        return newNote;
    }

}