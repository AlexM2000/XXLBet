package com.milto.servlet.command;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.command.context.HttpServletRequestContext;
import com.epam.xxlbet.milto.command.context.HttpServletResponseContext;
import com.epam.xxlbet.milto.command.context.RequestContext;
import com.epam.xxlbet.milto.command.context.ResponseContext;
import com.epam.xxlbet.milto.command.impl.GetHomeCommand;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.MatchesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static com.epam.xxlbet.milto.command.CommandResultType.FORWARD;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * GetHomePageCommandTest.
 *
 * @author Aliaksei Milto
 */
@RunWith(MockitoJUnitRunner.class)
public class GetHomePageCommandTest {
    private static final String EXPECTED_PAGE = "/views/index.jsp";

    private MatchesService matchesService = mock(MatchesService.class);

    private HttpServletRequest request = mock(HttpServletRequest.class);

    private HttpServletResponse response = mock(HttpServletResponse.class);

    private Command command = new GetHomeCommand(matchesService);

    private RequestContext requestContext;
    private ResponseContext responseContext;

    @Before
    public void setup() {
        requestContext = new HttpServletRequestContext(request);
        responseContext = new HttpServletResponseContext(response);

        // when
        when(matchesService.getIncompleteMatches()).thenReturn(new ArrayList<>());
    }

    @Test
    public void shouldReturnHomePage() throws ServiceException {
        CommandResult commandResult = command.execute(requestContext, responseContext);

        // then
        assertEquals(FORWARD, commandResult.getCommandResultType());
        assertEquals(EXPECTED_PAGE, commandResult.getPage());
    }
}
