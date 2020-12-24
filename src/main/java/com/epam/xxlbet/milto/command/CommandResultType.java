package com.epam.xxlbet.milto.command;

/**
 * CommandType.
 * WRITE_DIRECT_TO_RESPONSE means, that json is written using print() or jackson
 * and is processed by ajax in frontend.
 *
 * @author Aliaksei Milto
 */
public enum CommandResultType {
    REDIRECT, FORWARD, WRITE_DIRECT_TO_RESPONSE;
}
