package com.epam.xxlbet.milto.command.factory;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.impl.GetAllUserBetsCommand;
import com.epam.xxlbet.milto.command.impl.GetConfirmPageCommand;
import com.epam.xxlbet.milto.command.impl.GetDefeatUserBetsCommand;
import com.epam.xxlbet.milto.command.impl.GetHomeCommand;
import com.epam.xxlbet.milto.command.impl.GetLanguageCommand;
import com.epam.xxlbet.milto.command.impl.GetLoginPageCommand;
import com.epam.xxlbet.milto.command.impl.GetProfileCommand;
import com.epam.xxlbet.milto.command.impl.GetRegistrationPageCommand;
import com.epam.xxlbet.milto.command.impl.GetWinUserBetsCommand;
import com.epam.xxlbet.milto.command.impl.PostConfirmRegistrationCommand;
import com.epam.xxlbet.milto.command.impl.PostLoginCommand;
import com.epam.xxlbet.milto.command.impl.PostLogoutCommand;
import com.epam.xxlbet.milto.command.impl.PostRegistrationCommand;
import com.epam.xxlbet.milto.service.impl.BetsServiceImpl;
import com.epam.xxlbet.milto.service.impl.MatchesServiceImpl;
import com.epam.xxlbet.milto.service.impl.RoleServiceImpl;
import com.epam.xxlbet.milto.service.impl.StatusServiceImpl;
import com.epam.xxlbet.milto.service.impl.UserInfoServiceImpl;
import com.epam.xxlbet.milto.service.impl.UserServiceImpl;

/**
 * CommandFactoryImpl.
 *
 * @author Aliaksei Milto
 */
public class CommandFactoryImpl implements CommandFactory {
    private static CommandFactoryImpl instance;

    private CommandFactoryImpl() { }

    public static CommandFactoryImpl getInstance() {
        if (instance == null) {
            instance = new CommandFactoryImpl();
        }

        return instance;
    }

    @Override
    public Command createCommand(final String commandName) {
        Command command;
        switch (commandName) {
            case GET_HOME_COMMAND:
                command = new GetHomeCommand(MatchesServiceImpl.getInstance());
                break;
            case GET_LOGIN_PAGE:
                command = new GetLoginPageCommand();
                break;
            case POST_LOGIN:
                command = new PostLoginCommand(UserServiceImpl.getInstance(), StatusServiceImpl.getInstance(), RoleServiceImpl.getInstance());
                break;
            case POST_LOGOUT:
                command = new PostLogoutCommand();
                break;
            case LANGUAGE_COMMAND:
                command = new GetLanguageCommand();
                break;
            case GET_REGISTRATION_PAGE:
                command = new GetRegistrationPageCommand();
                break;
            case GET_CONFIRM_PAGE:
                command = new GetConfirmPageCommand();
                break;
            case POST_REGISTRATION_COMMAND:
                command = new PostRegistrationCommand(UserServiceImpl.getInstance());
                break;
            case POST_CONFIRM_COMMAND:
                command = new PostConfirmRegistrationCommand(UserServiceImpl.getInstance());
                break;
            case GET_PROFILE_PAGE:
                command = new GetProfileCommand(UserInfoServiceImpl.getInstance(), BetsServiceImpl.getInstance());
                break;
            case GET_ALL_USER_BETS:
                command = new GetAllUserBetsCommand(BetsServiceImpl.getInstance());
                break;
            case GET_WIN_USER_BETS:
                command = new GetWinUserBetsCommand(BetsServiceImpl.getInstance());
                break;
            case GET_DEFEAT_USER_BETS:
                command = new GetDefeatUserBetsCommand(BetsServiceImpl.getInstance());
                break;
            default:
                throw new IllegalArgumentException("Unknown command " + commandName);
        }

        return command;
    }
}
