package com.epam.xxlbet.milto.validator;

import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.utils.Errors;
import com.epam.xxlbet.milto.validator.impl.UserExistsValidator;
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
 * UserExistsValidatorTest.
 *
 * @author Aliaksei Milto
 */
@RunWith(MockitoJUnitRunner.class)
public class UserExistsValidatorTest {
    private static final String SOME_MAIL = "somemail";

    @InjectMocks
    private UserExistsValidator validator = UserExistsValidator.getInstance();

    @Mock
    private UserService userService;
    private Errors errors;

    @Before
    public void setup() {
        errors = new Errors();
        errors.setLocale("be");
    }

    @After
    public void verifyCalls() {
        // verify
        verify(userService, times(1)).getUserByEmail(SOME_MAIL);
    }

    @Test
    public void shouldNotValidate_UserExist() {
        User user = new User();

        // when
        when(userService.getUserByEmail(SOME_MAIL)).thenReturn(user);

        validator.validate(SOME_MAIL, errors);

        // then
        assertTrue(errors.hasErrors());
        assertEquals("Карыстальнік з адрасам электроннай пошты somemail ужо існуе", errors.getErrors().get("user.email.already.exists"));
    }

    @Test
    public void shouldtValidate_UserDoesNotExist() {

        // when
        when(userService.getUserByEmail(SOME_MAIL)).thenReturn(null);

        validator.validate(SOME_MAIL, errors);

        // then
        assertFalse(errors.hasErrors());
    }
}
