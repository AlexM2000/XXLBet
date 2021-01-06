package com.milto.validator;

import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.impl.UserNotExistsValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserNotExistsValidatorTest {
    @InjectMocks
    private UserNotExistsValidator validator = UserNotExistsValidator.getInstance();

    @Mock
    private UserService userService;

    @Test
    public void shouldNotValidate_UserExist() {
        Errors errors = new Errors();

        // when
        when(userService.getUserByEmail("somemail")).thenReturn(null);

        validator.validate("somemail", errors, "en");

        // then
        assertTrue(errors.hasErrors());
        assertEquals("User with email somemail doesn't exist", errors.getErrors().get("user.email.not.exists"));
    }

    @Test
    public void shouldNotValidate_UserDoesNotExist() {
        User user = new User();
        Errors errors = new Errors();

        // when
        when(userService.getUserByEmail("somemail")).thenReturn(user);

        validator.validate("somemail", errors, "en");

        // then
        assertFalse(errors.hasErrors());
    }
}
