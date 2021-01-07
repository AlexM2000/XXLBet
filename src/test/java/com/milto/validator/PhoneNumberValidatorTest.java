package com.milto.validator;

import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.impl.PhoneNumberValidator;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class PhoneNumberValidatorTest {
    private static final String VALID_PHONE_1 = "375292159909";
    private static final String VALID_PHONE_2 = "80337130497";
    private static final String VALID_PHONE_3 = "375256276629";
    private static final String VALID_PHONE_4 = "80441111111";
    private static final String INVALID_PHONE_1 = "375222159909";
    private static final String INVALID_PHONE_2 = "82337130497";
    private static final String INVALID_PHONE_3 = "3752562766";
    private static final String INVALID_PHONE_4 = "80441111111111";
    private static final String INVALID_PHONE_5 = "3462562766";
    private PhoneNumberValidator validator = PhoneNumberValidator.getInstance();
    private Errors errors;

    @Before
    public void setup() {
        errors = new Errors();
    }

    @Test
    public void shouldValidate_ValidPhone_1() {
        validator.validate(VALID_PHONE_1, errors, "ru");

        assertFalse(errors.hasErrors());
    }

    @Test
    public void shouldValidate_ValidPhone_2() {
        validator.validate(VALID_PHONE_2, errors, "ru");

        assertFalse(errors.hasErrors());
    }

    @Test
    public void shouldValidate_ValidPhone_3() {
        validator.validate(VALID_PHONE_3, errors, "ru");

        assertFalse(errors.hasErrors());
    }

    @Test
    public void shouldValidate_ValidPhone_4() {
        validator.validate(VALID_PHONE_4, errors, "ru");

        assertFalse(errors.hasErrors());
    }

    @Test
    public void shouldNotValidate_InvalidPhone_1() {
        validator.validate(INVALID_PHONE_1, errors, "ru");

        assertTrue(errors.hasErrors());
        assertNotNull(errors.getErrors().get("phonenumber.not.matches.regexp"));
        assertEquals("Вы ввели неправильный номер телефона", errors.getErrors().get("phonenumber.not.matches.regexp"));
    }

    @Test
    public void shouldNotValidate_InvalidPhone_2() {
        validator.validate(INVALID_PHONE_2, errors, "en");

        assertTrue(errors.hasErrors());
        assertNotNull(errors.getErrors().get("phonenumber.not.matches.regexp"));
        assertEquals("Invalid phone number", errors.getErrors().get("phonenumber.not.matches.regexp"));
    }

    @Test
    public void shouldNotValidate_InvalidPhone_3() {
        validator.validate(INVALID_PHONE_3, errors, "be");

        assertTrue(errors.hasErrors());
        assertNotNull(errors.getErrors().get("phonenumber.not.matches.regexp"));
        assertEquals("Несапраўдны нумар тэлефона", errors.getErrors().get("phonenumber.not.matches.regexp"));
    }

    @Test
    public void shouldNotValidate_InvalidPhone_4() {
        validator.validate(INVALID_PHONE_4, errors, "ru");

        assertTrue(errors.hasErrors());
        assertNotNull(errors.getErrors().get("phonenumber.not.matches.regexp"));
        assertEquals("Вы ввели неправильный номер телефона", errors.getErrors().get("phonenumber.not.matches.regexp"));
    }

    @Test
    public void shouldNotValidate_InvalidPhone_5() {
        validator.validate(INVALID_PHONE_5, errors, "ru");

        assertTrue(errors.hasErrors());
        assertNotNull(errors.getErrors().get("phonenumber.not.matches.regexp"));
        assertEquals("Вы ввели неправильный номер телефона", errors.getErrors().get("phonenumber.not.matches.regexp"));
    }
}
