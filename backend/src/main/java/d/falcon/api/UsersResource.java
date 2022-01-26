package d.falcon.api;

import d.falcon.model.NotesUser;
import d.falcon.util.PasswordUtils;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UsersResource {

    @Inject
    PasswordUtils passwordUtils;

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public NotesUser createUser(NotesUser user) {
        user.password = passwordUtils.encode(user.password);
        user.persist();
        return user;
    }

}
