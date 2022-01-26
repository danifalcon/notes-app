package d.falcon.api;

import d.falcon.api.payloads.LoginRequest;
import d.falcon.model.NotesUser;
import d.falcon.util.PasswordUtils;
import d.falcon.util.TokenUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/login")
public class LoginController {

    public static final long EXPIRATION_TIME = (24 * 60 * 60) * 10; //864_000 seconds +> 10 days

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    String jwtIssuer;

    @Inject
    PasswordUtils passwordUtils;

    @POST
    @Transactional
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String login(LoginRequest loginRequest) {
        NotesUser user = NotesUser.findByUsername(loginRequest.username)
                .orElseThrow(() -> new NotFoundException(NotesUser.class.getSimpleName()));

        if (!passwordUtils.verifyPasswords(loginRequest.password, user.password)) {
            throw new BadRequestException();
        }

        return TokenUtils.generateUserToken(user, EXPIRATION_TIME, jwtIssuer);
    }

}
