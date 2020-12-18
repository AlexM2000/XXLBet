package com.epam.xxlbet.milto.command.factory;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.impl.GetConfirmPageCommand;
import com.epam.xxlbet.milto.command.impl.GetHomeCommand;
import com.epam.xxlbet.milto.command.impl.GetLanguageCommand;
import com.epam.xxlbet.milto.command.impl.GetLoginPageCommand;
import com.epam.xxlbet.milto.command.impl.GetRegistrationPageCommand;
import com.epam.xxlbet.milto.command.impl.PostConfirmRegistrationCommand;
import com.epam.xxlbet.milto.command.impl.PostLoginCommand;
import com.epam.xxlbet.milto.command.impl.PostRegistrationCommand;
import com.epam.xxlbet.milto.service.impl.XxlMatchesServiceImpl;
import com.epam.xxlbet.milto.service.impl.XxlUserServiceImpl;

public class CommandFactoryImpl implements CommandFactory {
    private static final String GET_HOME_COMMAND = "home";
    private static final String LANGUAGE_COMMAND = "lang";
    private static final String GET_REGISTRATION_PAGE = "registration_page";
    private static final String POST_REGISTRATION_COMMAND = "registration";
    private static final String GET_CONFIRM_PAGE = "confirm_page";
    private static final String POST_CONFIRM_COMMAND = "confirm";
    private static final String GET_LOGIN_PAGE = "login_page";
    private static final String POST_LOGIN = "login";


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
                command = new GetHomeCommand(XxlMatchesServiceImpl.getInstance());
                break;
            case GET_LOGIN_PAGE:
                command = new GetLoginPageCommand();
                break;
            case POST_LOGIN:
                command = new PostLoginCommand(XxlUserServiceImpl.getInstance());
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
                command = new PostRegistrationCommand(XxlUserServiceImpl.getInstance());
                break;
            case POST_CONFIRM_COMMAND:
                command = new PostConfirmRegistrationCommand(XxlUserServiceImpl.getInstance());
                break;
            default:
                throw new IllegalArgumentException("Unknown command " + commandName);
        }

        return command;
    }
}
