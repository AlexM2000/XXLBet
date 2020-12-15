package com.epam.xxlbet.milto.command.factory;

import com.epam.xxlbet.milto.command.Command;

public interface CommandFactory {

    Command createCommand(String commandName);


}
