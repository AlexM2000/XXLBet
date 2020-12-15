package com.epam.xxlbet.milto.command;

import com.epam.xxlbet.milto.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

}
