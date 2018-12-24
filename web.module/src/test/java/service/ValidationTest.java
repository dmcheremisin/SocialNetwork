package service;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.social.network.utils.Validation.validateEmail;
import static com.social.network.utils.Validation.validatePassword;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidationTest {

    private static final List<String> WRONG_EMAILS = Arrays.asList(
            "aaaa", "aaaa@", "aaaa@aaaa", "aaaa@aaaa.", "1232", "123@", "123@123", "123@123.",
            "!aaa@aaa.ru", "aaa!aaa@aa.ru", "aaaa@aaaa!ru", "aaaa@aaaa!ru.com"
    );

    private static final List<String> RIGHT_EMAILS = Arrays.asList(
            "aaa@aaa.ru", "123@123.ru", "aaaa@aaa.ru.com", "aaa.aaa@aaa.aaa.ru", "aaa_aaa@aaaa.ru"
    );

    private static final List<String> WRONG_PASSWORDS = Arrays.asList(
            "a", "aaaaa", "aaaaaaaaa", "!aaaaaa", "1", "12345", "!123456", "123456789"
    );

    private static final List<String> RIGHT_PASSWORDS = Arrays.asList(
            "aaaaa1", "1aaaaa", "12345a", "a12345", "aaa1aa", "123a45"
    );


    @Test
    public void testEmailValidation() {
        WRONG_EMAILS.forEach(e -> assertFalse(validateEmail(e)));
        RIGHT_EMAILS.forEach(e -> assertTrue(validateEmail(e)));
    }

    @Test
    public void testPasswordValidation() {
        WRONG_PASSWORDS.forEach(p -> assertFalse(validatePassword(p)));
        RIGHT_PASSWORDS.forEach(p -> assertTrue(validatePassword(p)));
    }
}
