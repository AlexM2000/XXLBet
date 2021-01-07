package com.milto.servlet.command;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.command.impl.GetWinUserBetsCommand;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.epam.xxlbet.milto.command.CommandResultType.WRITE_DIRECT_TO_RESPONSE;
import static com.epam.xxlbet.milto.utils.DateUtils.convertToDateViaInstant;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * GetWinUserBetsCommandTest.
 *
 * @author Aliaksei Milto
 */
@RunWith(MockitoJUnitRunner.class)
public class GetWinUserBetsCommandTest extends AbstractUserBetsCommandTest {
    @InjectMocks
    private Command command = new GetWinUserBetsCommand(getMockBetsService());

    @Before
    public void setUp() {
        // when
        when(getMockBetsService().getWinningBetsByUser(SOME_LOGIN)).thenReturn(betResponses);
    }

    @Test
    public void shouldReturnCorrectResponse() throws ServiceException {
        betResponse.setMatch("team1 - team2");
        betResponse.setCoefficient(new BigDecimal(1));
        betResponse.setDateCreated(convertToDateViaInstant(LocalDate.of(2020, 12, 12)));
        betResponse.setWinningSum(new BigDecimal(300));
        betResponse.setSum(new BigDecimal(300));
        betResponses.add(betResponse);

        CommandResult commandResult = command.execute(requestContext, responseContext);

        // then
        assertEquals(WRITE_DIRECT_TO_RESPONSE, commandResult.getCommandResultType());
        assertEquals("[{\"match\":\"team1 - team2\",\"sum\":300,\"coefficient\":1,\"winningSum\":300,\"dateCreated\":1607720400000}]", stringWriter.toString());
    }

    @Test
    public void shouldReturnEmptyResponse() throws ServiceException {
        CommandResult commandResult = command.execute(requestContext, responseContext);

        // then
        assertEquals(WRITE_DIRECT_TO_RESPONSE, commandResult.getCommandResultType());
        assertEquals("[]", stringWriter.toString());
    }
}
