package com.epam.xxlbet.milto.validator;

import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.impl.PhoneNumberExistsValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * PhoneNumberValidatorTest.
 *
 * @author Aliaksei Milto
 */
@RunWith(MockitoJUnitRunner.class)
public class PhoneNumberExistsValidatorTest {
    private static final String TEST_PHONE_NUMBER = "SomePhoneNumber";

    @InjectMocks
    private PhoneNumberExistsValidator validator = PhoneNumberExistsValidator.getInstance();

    @Mock
    private UserService userService;

    private Errors errors;

    @Before
    public void setup() {
        errors = new Errors();
        errors.setLocale("ru");
    }

    @After
    public void verifyCalls() {
        // verify
        verify(userService, times(1)).isPhoneNumberExists(TEST_PHONE_NUMBER);
    }

    @Test
    public void shouldValidate_PhoneNumberDoesNotExist() {
        //when
        when(userService.isPhoneNumberExists(TEST_PHONE_NUMBER)).thenReturn(false);

        validator.validate(TEST_PHONE_NUMBER, errors);

        // then
        assertFalse(errors.hasErrors());
    }

    @Test
    public void shouldNotValidate_PhoneNumberExists() {
        // when
        when(userService.isPhoneNumberExists(TEST_PHONE_NUMBER)).thenReturn(true);

        validator.validate(TEST_PHONE_NUMBER, errors);

        // then
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getErrors().get("user.with.phonenumber.exists"));
        assertEquals("Пользователь с номером SomePhoneNumber уже существует", errors.getErrors().get("user.with.phonenumber.exists"));
    }
}
