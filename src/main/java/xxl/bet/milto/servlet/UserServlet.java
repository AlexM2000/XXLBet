package xxl.bet.milto.servlet;

import xxl.bet.milto.service.UserService;
import xxl.bet.milto.service.impl.XxlUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")
public class UserServlet extends AbstractServlet {
    private UserService userService = XxlUserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getMapper().writeValue(resp.getWriter(), userService.createUser("alexeymilto@gmail.com", "+375292159909", "1234567"));
    }
}
