package com.epam.xxlbet.milto.validator;

import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.impl.UserNotExistsValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * UserNotExistsValidatorTest.
 *
 * @author Aliaksei Milto
 */
@RunWith(MockitoJUnitRunner.class)
public class UserNotExistsValidatorTest {
    private static final String SOME_MAIL = "somemail";

    @InjectMocks
    private UserNotExistsValidator validator = UserNotExistsValidator.getInstance();

    @Mock
    private UserService userService;

    private Errors errors;

    @Before
    public void setup() {
        errors = new Errors();
        errors.setLocale("en");
    }

    @After
    public void verifyCalls() {
        // verify
        verify(userService, times(1)).getUserByEmail(SOME_MAIL);
    }

    @Test
    public void shouldNotValidate_UserExist() {
        // when
        when(userService.getUserByEmail(SOME_MAIL)).thenReturn(null);

        validator.validate(SOME_MAIL, errors);

        // then
        assertTrue(errors.hasErrors());
        assertEquals("User with email somemail doesn't exist", errors.getErrors().get("user.email.not.exists"));
    }

    @Test
    public void shouldNotValidate_UserDoesNotExist() {
        User user = new User();

        // when
        when(userService.getUserByEmail("somemail")).thenReturn(user);

        validator.validate("somemail", errors);

        // then
        assertFalse(errors.hasErrors());
    }
}
