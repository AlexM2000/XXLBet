package xxl.bet.milto.servlet;

import xxl.bet.milto.service.MatchesService;
import xxl.bet.milto.service.impl.XxlMatchesServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends AbstractServlet {
    private MatchesService matchesService = XxlMatchesServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("matches", matchesService.getIncompleteMatches());
        req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
    }
}
