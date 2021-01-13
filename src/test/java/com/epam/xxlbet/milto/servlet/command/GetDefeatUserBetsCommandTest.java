package com.epam.xxlbet.milto.servlet.command;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.impl.GetDefeatUserBetsCommand;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.epam.xxlbet.milto.utils.DateUtils.convertToDateViaInstant;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * GetDefeatUserBetsCommandTest.
 *
 * @author Aliaksei Milto
 */
@RunWith(MockitoJUnitRunner.class)
public class GetDefeatUserBetsCommandTest extends AbstractUserBetsCommandTest {
    @InjectMocks
    private Command command = new GetDefeatUserBetsCommand(getBetsService());

    @Before
    public void setUp() {
        // when
        when(getBetsService().getDefeatBetsByUser(SOME_LOGIN)).thenReturn(betResponses);
    }

    @After
    public void verifyCalls() {
        // verify
        verify(getBetsService(), times(1)).getDefeatBetsByUser(SOME_LOGIN);
    }

    @Test
    public void shouldReturnCorrectResponse() throws ServiceException {
        betResponse.setMatch("team1 - team2");
        betResponse.setCoefficient(new BigDecimal(1));
        betResponse.setDateCreated(convertToDateViaInstant(LocalDate.of(2020, 12, 12)));
        betResponse.setWinningSum(new BigDecimal(300));
        betResponse.setSum(new BigDecimal(300));
        betResponses.add(betResponse);

        commandResult = command.execute(requestContext, responseContext);
    }

    @Test
    public void shouldReturnEmptyResponse() throws ServiceException {
        commandResult = command.execute(requestContext, responseContext);
    }
}
