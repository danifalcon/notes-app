package d.falcon.api;

import d.falcon.model.Note;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
    public Note createNote(Note newNote) {
        newNote.persist();
        return newNote;
    }

}