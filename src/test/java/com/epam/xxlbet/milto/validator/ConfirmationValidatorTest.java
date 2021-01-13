package com.epam.xxlbet.milto.validator;


import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.impl.ConfirmationValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * ConfirmationValidatorTest.
 *
 * @author Aliaksei Milto
 */
@RunWith(MockitoJUnitRunner.class)
public class ConfirmationValidatorTest {
    private static final String TEST_MAIL = "somemail";

    @InjectMocks
    private ConfirmationValidator confirmationValidator = ConfirmationValidator.getInstance();

    @Mock
    private UserService service;

    private User user;
    private Errors errors;

    @Before
    public void setup() {
        user = new User();
        user.setEmail(TEST_MAIL);
        errors = new Errors();

        // when
        when(service.getUserByEmail(TEST_MAIL)).thenReturn(user);
    }

    @After
    public void verifyCalls() {
        // verify
        verify(service, times(1)).getUserByEmail(TEST_MAIL);
    }

    @Test
    public void shouldNotValidate_UserNotEnabled() {
        user.setEnabled(false);

        confirmationValidator.validate(user.getEmail(), errors, "ru");

        // then
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getErrors().get("user.please.confirm.registration"));
        assertEquals("Пожалуйста, подтвердите регистрацию по ссылке в электронном письме", errors.getErrors().get("user.please.confirm.registration"));
    }

    @Test
    public void shouldValidate_UserEnabled() {
        user.setEnabled(true);

        confirmationValidator.validate(user.getEmail(), errors, "ru");

        // then
        assertFalse(errors.hasErrors());
        assertNull(errors.getErrors().get("user.please.confirm.registration"));
    }
}
