package com.milto.validator;

import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.impl.UserExistsValidator;
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
public class UserExistsValidatorTest {
    @InjectMocks
    private UserExistsValidator validator = UserExistsValidator.getInstance();

    @Mock
    private UserService userService;

    @Test
    public void shouldNotValidate_UserExist() {
        User user = new User();
        Errors errors = new Errors();

        // when
        when(userService.getUserByEmail("somemail")).thenReturn(user);

        validator.validate("somemail", errors, "be");

        // then
        assertTrue(errors.hasErrors());
        assertEquals("Карыстальнік з адрасам электроннай пошты somemail ужо існуе", errors.getErrors().get("user.email.already.exists"));
    }

    @Test
    public void shouldtValidate_UserDoesNotExist() {
        Errors errors = new Errors();

        // when
        when(userService.getUserByEmail("somemail")).thenReturn(null);

        validator.validate("somemail", errors, "be");

        // then
        assertFalse(errors.hasErrors());
    }
}
