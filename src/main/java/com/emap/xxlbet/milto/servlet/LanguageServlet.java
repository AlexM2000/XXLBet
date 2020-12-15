package com.emap.xxlbet.milto.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/lang")
public class LanguageServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String locale = req.getParameter("lang") != null ? req.getParameter("lang") : "en";
        resp.addCookie(new Cookie("language", locale));
        resp.getWriter().print("ok");
        resp.getWriter().flush();
    }
}
