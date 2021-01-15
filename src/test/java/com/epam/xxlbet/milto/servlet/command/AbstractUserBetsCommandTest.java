package com.epam.xxlbet.milto.servlet.command;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.SimpleHttpServletRequestContext;
import com.epam.xxlbet.milto.context.HttpServletResponseContext;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.requestandresponsebody.BetResponse;
import com.epam.xxlbet.milto.service.BetsService;
import org.junit.After;
import org.junit.Before;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.epam.xxlbet.milto.command.CommandResultType.WRITE_DIRECT_TO_RESPONSE;
import static org.junit.Assert.assertEquals;
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

    protected CommandResult commandResult;
    protected RequestContext requestContext;
    protected ResponseContext responseContext;
    protected List<BetResponse> betResponses;
    protected BetResponse betResponse;

    @Before
    public void setup() {
        requestContext = new SimpleHttpServletRequestContext(request);
        responseContext = new HttpServletResponseContext(response);
        betResponses = new ArrayList<>();
        betResponse = new BetResponse();

        // when
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        when(request.getSession().getAttribute("login")).thenReturn(SOME_LOGIN);
    }

    @After
    public void checkResults() {
        // then
        assertEquals(commandResult.getCommandResultType(), WRITE_DIRECT_TO_RESPONSE);
        assertEquals(betResponses, commandResult.getResponseBody());
    }

    protected BetsService getBetsService() {
        return betsService;
    }
}
