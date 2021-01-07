package servlet.command;

import com.epam.xxlbet.milto.command.context.HttpServletRequestContext;
import com.epam.xxlbet.milto.command.context.HttpServletResponseContext;
import com.epam.xxlbet.milto.command.context.RequestContext;
import com.epam.xxlbet.milto.command.context.ResponseContext;
import com.epam.xxlbet.milto.command.impl.GetAllUserBetsCommand;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.requestandresponsebody.BetResponse;
import com.epam.xxlbet.milto.service.BetsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.xxlbet.milto.utils.DateUtils.convertToDateViaInstant;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetAllUserBetsCommandTest {
    private static final String SOME_LOGIN = "SomeLogin";

    private BetsService betsService = mock(BetsService.class);

    private HttpServletRequest request = mock(HttpServletRequest.class);

    private HttpServletResponse response = mock(HttpServletResponse.class);

    private RequestContext requestContext;
    private ResponseContext responseContext;
    private List<BetResponse> betResponses;
    private BetResponse betResponse;
    private StringWriter stringWriter;
    private PrintWriter writer;

    @InjectMocks
    private GetAllUserBetsCommand command = new GetAllUserBetsCommand(betsService);

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
        when(betsService.getBetsHistoryByUser(SOME_LOGIN)).thenReturn(betResponses);
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    public void shouldReturnCorrectResponse() throws ServiceException {
        betResponse.setMatch("team1 - team2");
        betResponse.setCoefficient(new BigDecimal(1));
        betResponse.setDateCreated(convertToDateViaInstant(LocalDate.of(2020, 12, 12)));
        betResponse.setWinningSum(new BigDecimal(300));
        betResponse.setSum(new BigDecimal(300));
        betResponses.add(betResponse);

        command.execute(requestContext, responseContext);

        assertEquals("[{\"match\":\"team1 - team2\",\"sum\":300,\"coefficient\":1,\"winningSum\":300,\"dateCreated\":1607720400000}]", stringWriter.toString());
    }

    @Test
    public void shouldReturnEmptyResponse() throws ServiceException {
        command.execute(requestContext, responseContext);

        assertEquals("[]", stringWriter.toString());
    }
}
