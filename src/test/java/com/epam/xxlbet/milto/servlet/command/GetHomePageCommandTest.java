package com.epam.xxlbet.milto.servlet.command;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.command.impl.GetHomeCommand;
import com.epam.xxlbet.milto.context.HttpServletRequestContext;
import com.epam.xxlbet.milto.context.HttpServletResponseContext;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.MatchesService;
import com.epam.xxlbet.milto.service.impl.MatchesServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.xxlbet.milto.command.CommandResultType.FORWARD;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * GetHomePageCommandTest.
 *
 * @author Aliaksei Milto
 */
@RunWith(MockitoJUnitRunner.class)
public class GetHomePageCommandTest {
    private static final String EXPECTED_PAGE = "/home";

    private MatchesService matchesService = MatchesServiceImpl.getInstance();

    private HttpServletRequest request = mock(HttpServletRequest.class);

    private HttpServletResponse response = mock(HttpServletResponse.class);

    private Command command = new GetHomeCommand(matchesService);

    private RequestContext requestContext;
    private ResponseContext responseContext;

    @Before
    public void setup() {
        requestContext = new HttpServletRequestContext(request);
        responseContext = new HttpServletResponseContext(response);
    }

    @Test
    public void shouldReturnHomePage() throws ServiceException {
        CommandResult commandResult = command.execute(requestContext, responseContext);

        // then
        assertEquals(FORWARD, commandResult.getCommandResultType());
        assertEquals(EXPECTED_PAGE, commandResult.getPage());
    }
}
