package com.epam.xxlbet.milto.dao.impl;

import com.epam.xxlbet.milto.dao.UserInfoDao;
import com.epam.xxlbet.milto.domain.UserInfo;
import com.epam.xxlbet.milto.populator.impl.ResultSetToUserInfoPopulator;
import com.epam.xxlbet.milto.utils.XxlBetConstants;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.INSERT_INTO_USER_INFO;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.SELECT_USER_INFO_BY_EMAIL;

/**
 * UserInfoDaoImpl.
 *
 * @author Aliaksei Milto
 */
public class UserInfoDaoImpl extends AbstractDao<UserInfo> implements UserInfoDao {
    private static UserInfoDaoImpl instance;

    private UserInfoDaoImpl() {
        super(XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_USER_INFO, ResultSetToUserInfoPopulator.getInstance());
    }

    public static UserInfoDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserInfoDaoImpl();
        }

        return instance;
    }

    @Override
    public UserInfo createNewUserInfo(UserInfo userInfo) {
        executeUpdate(
                INSERT_INTO_USER_INFO,
                userInfo.getEmail(),
                userInfo.getPhoneNumber(),
                userInfo.getSurname(),
                userInfo.getName(),
                userInfo.getSecondName(),
                userInfo.getBirthDate()
        );

        return getUserInfoByEmail(userInfo.getEmail());
    }

    @Override
    public UserInfo getUserInfoByEmail(String email) {
        return executeForSingleResult(SELECT_USER_INFO_BY_EMAIL, email);
    }
}
