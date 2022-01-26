package d.falcon.util;

import io.quarkus.elytron.security.common.BcryptUtil;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.wildfly.security.password.Password;
import org.wildfly.security.password.PasswordFactory;
import org.wildfly.security.password.WildFlyElytronPasswordProvider;
import org.wildfly.security.password.interfaces.BCryptPassword;
import org.wildfly.security.password.util.ModularCrypt;

import javax.enterprise.context.ApplicationScoped;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Logger;

@ApplicationScoped
public class PasswordUtils {

    private static final Logger LOG = Logger.getLogger(PasswordUtils.class.getName());

    @ConfigProperty(name = "org.acme.quarkus-jwt.password.iteration")
    Integer iteration;

    public String encode(String password) {
        return BcryptUtil.bcryptHash(password, iteration);
    }

    public boolean verifyPasswords(String originalPassword, String encryptedPassword) {
        try {
            WildFlyElytronPasswordProvider passwordProvider = new WildFlyElytronPasswordProvider();
            PasswordFactory passwordFactory = PasswordFactory.getInstance(
                    BCryptPassword.ALGORITHM_BCRYPT,
                    passwordProvider
            );
            Password passwordKey = ModularCrypt.decode(encryptedPassword);
            BCryptPassword restoredPassword = (BCryptPassword) passwordFactory.translate(passwordKey);
            return passwordFactory.verify(restoredPassword, originalPassword.toCharArray());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            LOG.severe(e.toString());
            return false;
        }
    }

}
