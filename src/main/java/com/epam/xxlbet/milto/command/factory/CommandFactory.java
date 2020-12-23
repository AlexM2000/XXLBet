package com.epam.xxlbet.milto.command.factory;

import com.epam.xxlbet.milto.command.Command;

/**
 * CommandFactory.
 *
 * @author Aliaksei Milto
 */
public interface CommandFactory {

    Command createCommand(String commandName);


}
