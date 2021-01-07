package com.epam.xxlbet.milto.validator;

import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.impl.PasswordValidator;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * PasswordValidatorTest.
 *
 * @author Aliaksei Milto
 */
public class PasswordValidatorTest {
    private static final String VALID_PASSWORD_1 = "1234_Ya";
    private static final String VALID_PASSWORD_2 = "IWannaMore__2";
    private static final String VALID_PASSWORD_3 = "Zaryazheniy___Leha";
    private static final String INVALID_PASSWORD_2 = "horse_in_the_coat";
    private static final String INVALID_PASSWORD_3 = "HORSEWITHCOAT";
    private static final String INVALID_PASSWORD_4 = "p1_uk";
    private static final String INVALID_PASSWORD_5 = "Otkryvau_Pivko_2281337";
    private PasswordValidator passwordValidator = PasswordValidator.getInstance();
    private Errors errors;

    @Before
    public void setup() {
        errors = new Errors();
    }

    @Test
    public void shouldValidate_ValidPassword1() {
        passwordValidator.validate(VALID_PASSWORD_1, errors, "ru");

        assertFalse(errors.hasErrors());
    }

    @Test
    public void shouldValidate_ValidPassword2() {
        passwordValidator.validate(VALID_PASSWORD_2, errors, "ru");

        assertFalse(errors.hasErrors());
    }

    @Test
    public void shouldValidate_ValidPassword3() {
        passwordValidator.validate(VALID_PASSWORD_3, errors, "ru");

        assertFalse(errors.hasErrors());
    }

    @Test
    public void shouldNotValidate_InvalidPassword2() {
        passwordValidator.validate(INVALID_PASSWORD_2, errors, "be");

        assertTrue(errors.hasErrors());
        assertEquals("Пароль павінен утрымліваць па меншай меры адну маленькую літару, адну вялікую літару, адзін спецыяльны сімвал, мінімум 7 максімум 20 сімвалаў", errors.getErrors().get("password.not.matches.regexp"));
    }

    @Test
    public void shouldNotValidate_InvalidPassword3() {
        passwordValidator.validate(INVALID_PASSWORD_3, errors, "en");

        assertTrue(errors.hasErrors());
        assertEquals("Password should contain at least one small letter, one big letter, one special character, minimum 7 maximum 20 symbols", errors.getErrors().get("password.not.matches.regexp"));
    }

    @Test
    public void shouldNotValidate_InvalidPassword4() {
        passwordValidator.validate(INVALID_PASSWORD_4, errors, "ru");

        assertTrue(errors.hasErrors());
        assertEquals("Пароль должен содержать хотя бы один маленький символ, один большой символ, один специальный символ, минимум 7 максимум 20 символов", errors.getErrors().get("password.not.matches.regexp"));
    }

    @Test
    public void shouldNotValidate_InvalidPassword5() {
        passwordValidator.validate(INVALID_PASSWORD_5, errors, "en");

        assertTrue(errors.hasErrors());
        assertEquals("Password should contain at least one small letter, one big letter, one special character, minimum 7 maximum 20 symbols", errors.getErrors().get("password.not.matches.regexp"));
    }
}
