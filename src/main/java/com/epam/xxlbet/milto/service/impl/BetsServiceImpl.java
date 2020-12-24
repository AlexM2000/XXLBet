package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.dao.BetsDao;
import com.epam.xxlbet.milto.dao.impl.BetsDaoImpl;
import com.epam.xxlbet.milto.domain.Bet;
import com.epam.xxlbet.milto.domain.Opponent;
import com.epam.xxlbet.milto.requestbody.BetResponse;
import com.epam.xxlbet.milto.service.BetsService;
import com.epam.xxlbet.milto.service.OpponentsService;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    private BetsServiceImpl() {
        betsDao = BetsDaoImpl.getInstance();
        opponentsService = OpponentsServiceImpl.getInstance();
    }

    public static BetsServiceImpl getInstance() {
        if (instance == null) {
            instance = new BetsServiceImpl();
        }

        return instance;
    }

    @Override
    public List<Bet> getAllIncompleteBetsByUser(String email, String phoneNumber) {
        return betsDao.getAllIncompleteBetsByUser(email, phoneNumber);
    }

    @Override
    public List<Bet> getBetsHistoryByUser(String email, String phoneNumber) {
        return betsDao.getBetsHistoryByUser(email, phoneNumber);
    }

    @Override
    public List<Bet> getIncompleteBets(String email, String phoneNumber) {
        return null;
    }

    @Override
    public List<Bet> getWinningBets(String email, String phoneNumber) {
        return null;
    }

    @Override
    public List<Bet> getDefeatBets(String email, String phoneNumber) {
        return null;
    }

    @Override
    public Bet createBet(long matchId, BigDecimal sum, long expectedWinnerId) {
        return null;
    }

    @Override
    public List<BetResponse> getBetsHistoryByUser(String email) {
        List<BetResponse> betResponses = new ArrayList<>();
        List<Bet> bets = betsDao.getBetsHistoryByUser(email, null);

        for (Bet bet : bets) {
            BetResponse betResponse = new BetResponse();
            List<Opponent> opponents = opponentsService.getOpponentsByMatchId(bet.getMatchId());
            betResponse.setMatch(opponents.stream().map(Opponent::getName).collect(Collectors.joining(" - ")));
            BigDecimal coefficient = opponentsService.getOpponentById(bet.getExpectedWinnerId()).getCoefficient();
            betResponse.setCoefficient(coefficient);
            betResponse.setSum(bet.getSum());
            betResponse.setWinningSum(coefficient.multiply(bet.getSum()));
            betResponses.add(betResponse);
        }

        return betResponses;
    }
}
