package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.dao.UserDao;
import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.domain.UserInfo;
import com.epam.xxlbet.milto.populator.Populator;
import com.epam.xxlbet.milto.requestandresponsebody.RegistrationRequest;
import com.epam.xxlbet.milto.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * UserServiceImplTest.
 *
 * @author Aliaksei Milto
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    private static final String SOME_MAIL = "somemail";
    @InjectMocks
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Mock
    private VerificationTokenService verificationTokenService;

    @Mock
    private UserDao userDao;

    @Mock
    private UserInfoService userInfoService;

    @Mock
    private Populator<RegistrationRequest, User> registrationToUserPopulator;

    @Mock
    private Populator<RegistrationRequest, UserInfo> registrationToUserInfoPopulator;

    private User user;
    private UserInfo userInfo;
    private RegistrationRequest registrationRequest;

    @Before
    public void setup() {
        user = new User();
        userInfo = new UserInfo();
        registrationRequest = new RegistrationRequest();
    }

    @Test
    public void shouldCreateUserSuccessfully() {
        registrationRequest.setEmail(SOME_MAIL);
        user.setEmail(SOME_MAIL);
        userInfo.setEmail(SOME_MAIL);

        // when
        when(userDao.getUserByEmail(SOME_MAIL)).thenReturn(user);

        user = userService.createNewUser(registrationRequest);

        // then
        assertEquals(SOME_MAIL, user.getEmail());

        // verify
        verify(userDao, times(1)).createUser(any(User.class));
        verify(registrationToUserPopulator, times(1)).populate(eq(registrationRequest), any(User.class));
        verify(registrationToUserInfoPopulator, times(1)).populate(eq(registrationRequest), any(UserInfo.class));
        verify(userInfoService, times(1)).createNewUserInfo(any(UserInfo.class));
        verify(userDao, times(1)).getUserByEmail(SOME_MAIL);
    }
}
