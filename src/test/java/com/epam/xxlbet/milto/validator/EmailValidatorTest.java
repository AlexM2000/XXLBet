package com.epam.xxlbet.milto.validator;

import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.impl.EmailValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * EmailValidatorTest.
 *
 * @author Aliaksei Milto
 */
public class EmailValidatorTest {
    private static final String VALID_MAIL_1 = "alexeymilto@gmail.com";
    private static final String VALID_MAIL_2 = "vlad1234@yandex.by";
    private static final String VALID_MAIL_3 = "AlEx301123@mail.ru";
    private static final String INVALID_MAIL_1 = "somemail@.com";
    private static final String INVALID_MAIL_2 = "somemail@com.";
    private static final String INVALID_MAIL_3 = "@jaba.de";
    private static final String INVALID_MAIL_4 = "somemailcom";
    private EmailValidator validator = EmailValidator.getInstance();
    private Errors errors;

    @Before
    public void setup() {
        errors = new Errors();
    }

    @Test
    public void shouldValidate_ValidEmail1() {
        validator.validate(VALID_MAIL_1, errors);

        assertFalse(errors.hasErrors());
    }

    @Test
    public void shouldValidate_ValidEmail2() {
        validator.validate(VALID_MAIL_2, errors);

        assertFalse(errors.hasErrors());
    }

    @Test
    public void shouldValidate_ValidEmail3() {
        validator.validate(VALID_MAIL_3, errors);

        assertFalse(errors.hasErrors());
    }

    @Test
    public void shouldNotValidate_InvalidEmail1() {
        errors.setLocale("ru");
        validator.validate(INVALID_MAIL_1, errors);

        assertTrue(errors.hasErrors());
        assertNotNull(errors.getErrors().get("email.not.matches.regexp"));
        assertEquals("Вы ввели неправильный адрес электронной почты", errors.getErrors().get("email.not.matches.regexp"));
    }

    @Test
    public void shouldNotValidate_InvalidEmail2() {
        errors.setLocale("ru");
        validator.validate(INVALID_MAIL_2, errors);

        assertTrue(errors.hasErrors());
        assertNotNull(errors.getErrors().get("email.not.matches.regexp"));
        assertEquals("Вы ввели неправильный адрес электронной почты", errors.getErrors().get("email.not.matches.regexp"));
    }

    @Test
    public void shouldNotValidate_InvalidEmail3() {
        errors.setLocale("be");
        validator.validate(INVALID_MAIL_3, errors);

        assertTrue(errors.hasErrors());
        assertNotNull(errors.getErrors().get("email.not.matches.regexp"));
        assertEquals("Няправільны адрас электроннай пошты", errors.getErrors().get("email.not.matches.regexp"));
    }


    @Test
    public void shouldNotValidate_InvalidEmail4() {
        errors.setLocale("en");
        validator.validate(INVALID_MAIL_4, errors);

        assertTrue(errors.hasErrors());
        assertNotNull(errors.getErrors().get("email.not.matches.regexp"));
        assertEquals("Invalid email", errors.getErrors().get("email.not.matches.regexp"));
    }
}
