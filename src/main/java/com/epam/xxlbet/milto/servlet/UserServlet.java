package com.epam.xxlbet.milto.servlet;

import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.service.impl.XxlUserServiceImpl;

import javax.servlet.annotation.WebServlet;

@WebServlet("/user")
public class UserServlet extends DispatcherServlet {
    private UserService userService = XxlUserServiceImpl.getInstance();

    /*
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getMapper().writeValue(resp.getWriter(), userService.createNewUser("alexeymilto@gmail.com", "+375292159909", "1234567"));
    }
    */

}
