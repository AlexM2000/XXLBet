package com.emap.xxlbet.milto.servlet;

import com.emap.xxlbet.milto.service.UserService;
import com.emap.xxlbet.milto.service.impl.XxlUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends AbstractServlet {
    private UserService userService = XxlUserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getMapper().writeValue(resp.getWriter(), userService.createNewUser("alexeymilto@gmail.com", "+375292159909", "1234567"));
    }
}
