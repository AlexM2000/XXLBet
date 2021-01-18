package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.UserInfoDao;
import com.epam.xxlbet.milto.dao.impl.UserInfoDaoImpl;
import com.epam.xxlbet.milto.domain.UserInfo;
import com.epam.xxlbet.milto.requestandresponsebody.BookmakerPageUserResponse;
import com.epam.xxlbet.milto.service.RoleService;
import com.epam.xxlbet.milto.service.StatusService;
import com.epam.xxlbet.milto.service.UserInfoService;

import java.util.ArrayList;
import java.util.List;

/**
 * UserInfoServiceImpl.
 *
 * @author Aliaksei Milto
 */
public class UserInfoServiceImpl implements UserInfoService {
    private static UserInfoServiceImpl instance;
    private RoleService roleService;
    private StatusService statusService;
    private UserInfoDao userInfoDao;

    private UserInfoServiceImpl() {
        userInfoDao = UserInfoDaoImpl.getInstance();
        statusService = StatusServiceImpl.getInstance();
        roleService = RoleServiceImpl.getInstance();
    }

    public static UserInfoServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserInfoServiceImpl();
        }

        return instance;
    }

    @Override
    public UserInfo getUserInfoByEmail(String email) {
        return userInfoDao.getUserInfoByEmail(email);
    }

    @Override
    public UserInfo createNewUserInfo(UserInfo userInfo) {
        return userInfoDao.createNewUserInfo(userInfo);
    }

    @Override
    public UserInfo updateUserInfo(UserInfo userInfo) {
        return userInfoDao.updateUserInfo(userInfo);
    }

    @Override
    public List<BookmakerPageUserResponse> getAllUserInfoForAdminPage() {
        List<UserInfo> userInfoList = userInfoDao.getAllUsers();
        List<BookmakerPageUserResponse> userResponses = new ArrayList<>();

        for (UserInfo userInfo : userInfoList) {
            BookmakerPageUserResponse userResponse = new BookmakerPageUserResponse();
            userResponse.setEmail(userInfo.getEmail());
            userResponse.setPhoneNumber(userInfo.getPhoneNumber());
            userResponse.setRole(roleService.getRoleById((long) userInfo.getRoleId()).getName());
            userResponse.setStatus(statusService.getStatusById((long) userInfo.getStatusId()).getName());
            userResponses.add(userResponse);
        }

        return userResponses;
    }

    @Override
    public void updateAllUsersBalanceAfterMatchComplete(Long matchId, Long matchResultId) {
        userInfoDao.updateAllUsersBalanceAfterMatchComplete(matchId, matchResultId);
    }
}
