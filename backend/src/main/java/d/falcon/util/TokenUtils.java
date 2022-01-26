package d.falcon.util;

import d.falcon.model.NotesUser;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;

import javax.json.Json;

public class TokenUtils {

    public static String generateUserToken(NotesUser user, Long duration, String issuer) {
        JwtClaimsBuilder jwt = Jwt.claims(Json.createObjectBuilder()
                .add("email", user.email)
                .build());

        jwt.issuer(issuer);
        jwt.subject(user.id.toString());
        jwt.upn(user.username);
        jwt.groups(String.valueOf(user.role));
        jwt.issuedAt(currentTimeInSecs());
        jwt.expiresAt(currentTimeInSecs() + duration);

        return jwt.sign();
    }

    private static int currentTimeInSecs() {
        return (int) (System.currentTimeMillis() / 1000);
    }

}
