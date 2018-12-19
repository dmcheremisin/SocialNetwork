package service;

import com.social.network.utils.Encryption;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

public class EncryptionTest {
    @Test
    public void testEncryptPassword() {
        String password1 = "!1@2#3$4aaa5bbb6ccc789";
        String password2 = "1232132";

        String encryptedPassword1 = Encryption.encryptPassword(password1);
        String encryptedPassword2 = Encryption.encryptPassword(password1);
        assertEquals(encryptedPassword1, encryptedPassword2);

        String encryptedPassword3 = Encryption.encryptPassword(password2);
        assertFalse(encryptedPassword1.equals(encryptedPassword3));
    }

}
