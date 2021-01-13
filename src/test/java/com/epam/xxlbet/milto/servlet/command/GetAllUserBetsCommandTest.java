package com.epam.xxlbet.milto.servlet.command;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.impl.GetAllUserBetsCommand;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.epam.xxlbet.milto.utils.DateUtils.convertToDateViaInstant;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * GetAllUserBetsCommandTest.
 *
 * @author Aliaksei Milto
 */
@RunWith(MockitoJUnitRunner.class)
public class GetAllUserBetsCommandTest extends AbstractUserBetsCommandTest {
    @InjectMocks
    private Command command = new GetAllUserBetsCommand(getBetsService());

    @Before
    public void setUp() {
        // when
        when(getBetsService().getBetsHistoryByUser(SOME_LOGIN)).thenReturn(betResponses);
    }

    @After
    public void verifyCalls() throws IOException {
        // verify
        verify(getBetsService(), times(1)).getBetsHistoryByUser(SOME_LOGIN);
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
