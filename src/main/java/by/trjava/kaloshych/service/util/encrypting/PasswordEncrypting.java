package by.trjava.kaloshych.service.util.encrypting;


import by.trjava.kaloshych.service.exception.EncryptException;
import org.apache.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import static java.nio.charset.StandardCharsets.UTF_8;

    /**
     * Class is used password hashing.
     *
     * @author Katsiaryna Kaloshych
     * @version 1.0
     * @since JDK1.0
     */
    public class PasswordEncrypting {
        private static final Logger logger = Logger.getLogger(PasswordEncrypting.class);
        private static final String KEY_PATTERN = "PBKDF2WithHmacSHA1";
        private static final int ITERATION_COUNT = 65536;
        private static final int KEY_LENGTH = 128;
        /**
         * Generate hash-string on base of given parameters (login and password).
         * Salt is generated on base of login (because login is unchangeable),
         * then password is encoded and returned.
         *
         * @param login    user's login.
         * @param password user's password.
         * @return encoded password.
         */
        public String generateHash(String login, String password) throws EncryptException {
            final byte[] salt = login.getBytes();
            final KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);

            try {
                SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_PATTERN);
                byte[] encoded = factory.generateSecret(spec).getEncoded();
                return new String(encoded, UTF_8);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                logger.fatal(e);
                throw new EncryptException(e);
            }
        }

private PasswordEncrypting(){}
private static PasswordEncrypting instance=new PasswordEncrypting();
        public static PasswordEncrypting getInstance(){
            return instance;}
        }

