package servlet.command;

import com.epam.xxlbet.milto.command.context.HttpServletRequestContext;
import com.epam.xxlbet.milto.command.context.HttpServletResponseContext;
import com.epam.xxlbet.milto.command.context.RequestContext;
import com.epam.xxlbet.milto.command.context.ResponseContext;
import com.epam.xxlbet.milto.requestandresponsebody.BetResponse;
import com.epam.xxlbet.milto.service.BetsService;
import org.junit.Before;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * AbstractUserBetsCommandTest.
 *
 * @author Aliaksei Milto
 */
public abstract class AbstractUserBetsCommandTest {
    protected static final String SOME_LOGIN = "SomeLogin";

    private BetsService betsService = mock(BetsService.class);

    private HttpServletRequest request = mock(HttpServletRequest.class);

    private HttpServletResponse response = mock(HttpServletResponse.class);

    protected RequestContext requestContext;
    protected ResponseContext responseContext;
    protected List<BetResponse> betResponses;
    protected BetResponse betResponse;
    protected StringWriter stringWriter;
    private PrintWriter writer;

    @Before
    public void setup() throws IOException {
        requestContext = new HttpServletRequestContext(request);
        responseContext = new HttpServletResponseContext(response);
        betResponses = new ArrayList<>();
        betResponse = new BetResponse();

        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);

        // when
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        when(request.getSession().getAttribute("login")).thenReturn(SOME_LOGIN);
        when(response.getWriter()).thenReturn(writer);
    }

    protected BetsService getMockBetsService() {
        return betsService;
    }
}
