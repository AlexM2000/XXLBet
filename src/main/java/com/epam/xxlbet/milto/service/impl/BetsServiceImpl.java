package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.BetsDao;
import com.epam.xxlbet.milto.dao.impl.BetsDaoImpl;
import com.epam.xxlbet.milto.domain.Bet;
import com.epam.xxlbet.milto.domain.Opponent;
import com.epam.xxlbet.milto.requestandresponsebody.BetResponse;
import com.epam.xxlbet.milto.requestandresponsebody.CreateBetRequest;
import com.epam.xxlbet.milto.service.BetsService;
import com.epam.xxlbet.milto.service.OpponentsService;
import com.epam.xxlbet.milto.service.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BetsServiceImpl.
 *
 * @author Aliaksei Milto
 */
public class BetsServiceImpl implements BetsService {
    private static BetsServiceImpl instance;
    private BetsDao betsDao;
    private OpponentsService opponentsService;
    private UserService userService;

    private BetsServiceImpl() {
        betsDao = BetsDaoImpl.getInstance();
        opponentsService = OpponentsServiceImpl.getInstance();
        userService = UserServiceImpl.getInstance();
    }

    public static BetsServiceImpl getInstance() {
        if (instance == null) {
            instance = new BetsServiceImpl();
        }

        return instance;
    }

    @Override
    public List<Bet> getIncompleteBets(String email, String phoneNumber) {
        return null;
    }

    @Override
    public List<BetResponse> getWinningBetsByUser(String email) {
        List<BetResponse> betResponses = new ArrayList<>();
        populateBetResponses(betsDao.getWinningBetsByUser(email), betResponses);
        return betResponses;
    }

    @Override
    public List<BetResponse> getDefeatBetsByUser(String email) {
        List<BetResponse> betResponses = new ArrayList<>();
        populateBetResponses(betsDao.getDefeatBetsByUser(email), betResponses);
        return betResponses;
    }

    @Override
    public Bet createBet(CreateBetRequest createBetRequest) {
        Bet bet = new Bet();
        bet.setDateCreated(new Date());
        bet.setExpectedWinnerId(createBetRequest.getExpectedWinnerId());
        bet.setMatchId(createBetRequest.getMatchId());
        bet.setSum(createBetRequest.getSum());
        bet.setUserId(userService.getUserByEmail(createBetRequest.getEmail()).getId());
        return betsDao.createBet(bet);
    }

    @Override
    public List<BetResponse> getBetsHistoryByUser(String email) {
        List<BetResponse> betResponses = new ArrayList<>();
        populateBetResponses(betsDao.getBetsHistoryByUser(email), betResponses);
        return betResponses;
    }

    /**
     * Transfers data from List<Bet> to List<BetResponse>
     *
     * @param bets List<Bets> with data
     * @param betResponses List<BetResponse>
     */
    private void populateBetResponses(List<Bet> bets, List<BetResponse> betResponses) {
        for (Bet bet : bets) {
            BetResponse betResponse = new BetResponse();
            List<Opponent> opponents = opponentsService.getOpponentsByMatchId(bet.getMatchId());
            betResponse.setMatch(opponents.stream().map(Opponent::getName).collect(Collectors.joining(" - ")));
            BigDecimal coefficient = opponentsService.getOpponentById(bet.getExpectedWinnerId()).getCoefficient();
            betResponse.setCoefficient(coefficient);
            betResponse.setSum(bet.getSum());
            betResponse.setWinningSum(coefficient.multiply(bet.getSum()));
            betResponse.setDateCreated(bet.getDateCreated());
            betResponses.add(betResponse);
        }
    }
}
