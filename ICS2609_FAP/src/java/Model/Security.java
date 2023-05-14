
package Model;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.codec.binary.*;

public class Security implements ServletContextListener {

    // Public/secret key component
    private static byte[] key;

    // ENCRYPT METHOD
    public static String encrypt(String strToEncrypt) {
        String encryptedString = null;
        try {
            // Cipher used: AES/ECB/PKCS5Padding
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return encryptedString;
    }

    // DECRYPT METHOD
    public static String decrypt(String codeDecrypt) {
        String decryptedString = null;
        try {
            // Cipher used: AES/ECB/PKCS5Padding
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptedString = new String(cipher.doFinal(Base64.decodeBase64(codeDecrypt)));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return decryptedString;
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        String keyString = context.getInitParameter("security_key");
        key = keyString.getBytes();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Do nothing
    }
}

