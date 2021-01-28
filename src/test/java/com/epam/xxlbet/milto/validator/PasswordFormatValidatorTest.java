package com.epam.xxlbet.milto.validator;

import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.impl.PasswordFormatValidator;
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
public class PasswordFormatValidatorTest {
    private static final String VALID_PASSWORD_1 = "1234_Ya";
    private static final String VALID_PASSWORD_2 = "IWannaMore__2";
    private static final String VALID_PASSWORD_3 = "Zaryazheniy___Leha";
    private static final String INVALID_PASSWORD_2 = "horse_in_the_coat";
    private static final String INVALID_PASSWORD_3 = "HORSEWITHCOAT";
    private static final String INVALID_PASSWORD_4 = "p1_uk";
    private static final String INVALID_PASSWORD_5 = "Otkryvau_Pivko_2281337";
    private PasswordFormatValidator passwordFormatValidator = PasswordFormatValidator.getInstance();
    private Errors errors;

    @Before
    public void setup() {
        errors = new Errors();
    }

    @Test
    public void shouldValidate_ValidPassword1() {
        errors.setLocale("ru");
        passwordFormatValidator.validate(VALID_PASSWORD_1, errors);

        assertFalse(errors.hasErrors());
    }

    @Test
    public void shouldValidate_ValidPassword2() {
        errors.setLocale("ru");
        passwordFormatValidator.validate(VALID_PASSWORD_2, errors);

        assertFalse(errors.hasErrors());
    }

    @Test
    public void shouldValidate_ValidPassword3() {
        errors.setLocale("ru");
        passwordFormatValidator.validate(VALID_PASSWORD_3, errors);

        assertFalse(errors.hasErrors());
    }

    @Test
    public void shouldNotValidate_InvalidPassword2() {
        errors.setLocale("be");
        passwordFormatValidator.validate(INVALID_PASSWORD_2, errors);

        assertTrue(errors.hasErrors());
        assertEquals("Пароль павінен утрымліваць па меншай меры адну маленькую літару, адну вялікую літару, адзін спецыяльны сімвал, мінімум 7 максімум 20 сімвалаў", errors.getErrors().get("password.not.matches.regexp"));
    }

    @Test
    public void shouldNotValidate_InvalidPassword3() {
        errors.setLocale("en");
        passwordFormatValidator.validate(INVALID_PASSWORD_3, errors);

        assertTrue(errors.hasErrors());
        assertEquals("Password should contain at least one small letter, one big letter, one special character, minimum 7 maximum 20 symbols", errors.getErrors().get("password.not.matches.regexp"));
    }

    @Test
    public void shouldNotValidate_InvalidPassword4() {
        errors.setLocale("ru");
        passwordFormatValidator.validate(INVALID_PASSWORD_4, errors);

        assertTrue(errors.hasErrors());
        assertEquals("Пароль должен содержать хотя бы один маленький символ, один большой символ, один специальный символ, минимум 7 максимум 20 символов", errors.getErrors().get("password.not.matches.regexp"));
    }

    @Test
    public void shouldNotValidate_InvalidPassword5() {
        errors.setLocale("en");
        passwordFormatValidator.validate(INVALID_PASSWORD_5, errors);

        assertTrue(errors.hasErrors());
        assertEquals("Password should contain at least one small letter, one big letter, one special character, minimum 7 maximum 20 symbols", errors.getErrors().get("password.not.matches.regexp"));
    }
}
