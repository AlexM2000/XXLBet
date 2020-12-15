package com.emap.xxlbet.milto.servlet;

import com.emap.xxlbet.milto.service.impl.XxlMatchesServiceImpl;
import com.emap.xxlbet.milto.service.MatchesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = {"/home"})
public class HomeServlet extends AbstractServlet {
    private MatchesService matchesService;

    @Override
    public void init() throws ServletException {
        getLogger().debug("Initializing HomeServlet...");
        matchesService  = XxlMatchesServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("matches", matchesService.getIncompleteMatches());
        req.setAttribute("today", LocalDateTime.now());
        req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
    }
}
