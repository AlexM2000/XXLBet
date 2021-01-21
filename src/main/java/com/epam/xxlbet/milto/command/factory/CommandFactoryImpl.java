package com.epam.xxlbet.milto.command.factory;

import com.epam.xxlbet.milto.command.Command;
import com.epam.xxlbet.milto.command.impl.GetAdminPageCommand;
import com.epam.xxlbet.milto.command.impl.GetAllUserBetsCommand;
import com.epam.xxlbet.milto.command.impl.GetBookmakerPageCommand;
import com.epam.xxlbet.milto.command.impl.GetCreateBetPageCommand;
import com.epam.xxlbet.milto.command.impl.GetCreateTeamPageCommand;
import com.epam.xxlbet.milto.command.impl.GetCreateTournamentPageCommand;
import com.epam.xxlbet.milto.command.impl.GetDefeatUserBetsCommand;
import com.epam.xxlbet.milto.command.impl.GetDoChangePasswordPageCommand;
import com.epam.xxlbet.milto.command.impl.GetHomeCommand;
import com.epam.xxlbet.milto.command.impl.GetLanguageCommand;
import com.epam.xxlbet.milto.command.impl.GetMatchesByTournamentCommand;
import com.epam.xxlbet.milto.command.impl.GetOpponentsByTournamentCommand;
import com.epam.xxlbet.milto.command.impl.GetProfileCommand;
import com.epam.xxlbet.milto.command.impl.GetUnlinkCreditCardPageCommand;
import com.epam.xxlbet.milto.command.impl.GetTournamentsBySportCommand;
import com.epam.xxlbet.milto.command.impl.GetWinUserBetsCommand;
import com.epam.xxlbet.milto.command.impl.PostChangePasswordCommand;
import com.epam.xxlbet.milto.command.impl.PostConfirmRegistrationCommand;
import com.epam.xxlbet.milto.command.impl.PostCreateBetCommand;
import com.epam.xxlbet.milto.command.impl.PostCreateChangePasswordRequestCommand;
import com.epam.xxlbet.milto.command.impl.PostLinkCreditCardCommand;
import com.epam.xxlbet.milto.command.impl.PostCreateMatchCommand;
import com.epam.xxlbet.milto.command.impl.PostCreateSportCommand;
import com.epam.xxlbet.milto.command.impl.PostCreateTeamCommand;
import com.epam.xxlbet.milto.command.impl.PostCreateTournamentCommand;
import com.epam.xxlbet.milto.command.impl.PostLoginCommand;
import com.epam.xxlbet.milto.command.impl.PostLogoutCommand;
import com.epam.xxlbet.milto.command.impl.PostPayBalanceCommand;
import com.epam.xxlbet.milto.command.impl.PostRegistrationCommand;
import com.epam.xxlbet.milto.command.impl.PostChangeUserRoleAndStatusCommand;
import com.epam.xxlbet.milto.command.impl.PostUnlinkCreditCardCommand;
import com.epam.xxlbet.milto.command.impl.GetPageCommand;
import com.epam.xxlbet.milto.exceptions.UnknownCommandException;
import com.epam.xxlbet.milto.service.impl.BetsServiceImpl;
import com.epam.xxlbet.milto.service.impl.CreditCardServiceImpl;
import com.epam.xxlbet.milto.service.impl.JavaxEmailSenderImpl;
import com.epam.xxlbet.milto.service.impl.MatchesServiceImpl;
import com.epam.xxlbet.milto.service.impl.OpponentsServiceImpl;
import com.epam.xxlbet.milto.service.impl.PasswordChangeRequestServiceImpl;
import com.epam.xxlbet.milto.service.impl.RoleServiceImpl;
import com.epam.xxlbet.milto.service.impl.SportServiceImpl;
import com.epam.xxlbet.milto.service.impl.StatusServiceImpl;
import com.epam.xxlbet.milto.service.impl.TournamentServiceImpl;
import com.epam.xxlbet.milto.service.impl.UserInfoServiceImpl;
import com.epam.xxlbet.milto.service.impl.UserServiceImpl;

/**
 * CommandFactoryImpl.
 *
 * @author Aliaksei Milto
 */
public class CommandFactoryImpl implements CommandFactory {
    private static CommandFactoryImpl instance;

    private CommandFactoryImpl() { }

    public static CommandFactoryImpl getInstance() {
        if (instance == null) {
            instance = new CommandFactoryImpl();
        }

        return instance;
    }

    @Override
    public Command createCommand(final String commandName) {
        Command command;
        switch (commandName) {
            default:
                throw new UnknownCommandException("Unknown command " + commandName);
            case GET_HOME_COMMAND:
                command = new GetHomeCommand(MatchesServiceImpl.getInstance());
                break;
            case GET_LOGIN_PAGE:
                command = new GetPageCommand("/login");
                break;
            case POST_LOGIN:
                command = new PostLoginCommand(UserServiceImpl.getInstance(), StatusServiceImpl.getInstance(), RoleServiceImpl.getInstance());
                break;
            case POST_LOGOUT:
                command = new PostLogoutCommand();
                break;
            case LANGUAGE_COMMAND:
                command = new GetLanguageCommand();
                break;
            case GET_BET_CREATE_PAGE:
                command = new GetCreateBetPageCommand(SportServiceImpl.getInstance());
                break;
            case POST_CREATE_BET:
                command = new PostCreateBetCommand(UserInfoServiceImpl.getInstance(), BetsServiceImpl.getInstance());
                break;
            case GET_REGISTRATION_PAGE:
                command = new GetPageCommand("/registration");
                break;
            case GET_CONFIRM_PAGE:
                command = new GetPageCommand("/confirm");
                break;
            case POST_REGISTRATION_COMMAND:
                command = new PostRegistrationCommand(UserServiceImpl.getInstance());
                break;
            case POST_CONFIRM_COMMAND:
                command = new PostConfirmRegistrationCommand(UserServiceImpl.getInstance());
                break;
            case GET_PROFILE_PAGE:
                command = new GetProfileCommand(
                        UserInfoServiceImpl.getInstance(),
                        BetsServiceImpl.getInstance(),
                        CreditCardServiceImpl.getInstance()
                );
                break;
            case GET_ALL_USER_BETS:
                command = new GetAllUserBetsCommand(BetsServiceImpl.getInstance());
                break;
            case GET_WIN_USER_BETS:
                command = new GetWinUserBetsCommand(BetsServiceImpl.getInstance());
                break;
            case GET_DEFEAT_USER_BETS:
                command = new GetDefeatUserBetsCommand(BetsServiceImpl.getInstance());
                break;
            case GET_ADMIN_PAGE:
                command = new GetAdminPageCommand(SportServiceImpl.getInstance());
                break;
            case GET_TOURNAMENTS_BY_SPORT:
                command = new GetTournamentsBySportCommand(TournamentServiceImpl.getInstance());
                break;
            case GET_OPPONENTS_BY_TOURNAMENT:
                command = new GetOpponentsByTournamentCommand(OpponentsServiceImpl.getInstance());
                break;
            case GET_MATCHES_BY_TOURNAMENT:
                command = new GetMatchesByTournamentCommand(MatchesServiceImpl.getInstance());
                break;
            case POST_CREATE_MATCH:
                command = new PostCreateMatchCommand(OpponentsServiceImpl.getInstance(), MatchesServiceImpl.getInstance());
                break;
            case GET_BOOKMAKER_PAGE:
                command = new GetBookmakerPageCommand(UserInfoServiceImpl.getInstance());
                break;
            case POST_CHANGE_USER_ROLE_AND_STATUS:
                command = new PostChangeUserRoleAndStatusCommand(
                        UserInfoServiceImpl.getInstance(),
                        RoleServiceImpl.getInstance(),
                        StatusServiceImpl.getInstance()
                );
                break;
            case GET_CREATE_SPORT_PAGE:
                command = new GetPageCommand("/create-sport");
                break;
            case POST_CREATE_SPORT:
                command = new PostCreateSportCommand(SportServiceImpl.getInstance());
                break;
            case GET_CREATE_TOURNAMENT_PAGE:
                command = new GetCreateTournamentPageCommand(SportServiceImpl.getInstance());
                break;
            case POST_CREATE_TOURNAMENT:
                command = new PostCreateTournamentCommand(TournamentServiceImpl.getInstance());
                break;
            case GET_CREATE_TEAM_PAGE:
                command = new GetCreateTeamPageCommand(SportServiceImpl.getInstance());
                break;
            case POST_CREATE_TEAM:
                command = new PostCreateTeamCommand(OpponentsServiceImpl.getInstance());
                break;
            case GET_LINK_CREDIT_CARD_PAGE:
                command = new GetPageCommand("/link-credit-card");
                break;
            case POST_LINK_CREDIT_CARD:
                command = new PostLinkCreditCardCommand(CreditCardServiceImpl.getInstance());
                break;
            case GET_UNLINK_CREDIT_CARD_PAGE:
                command = new GetUnlinkCreditCardPageCommand(CreditCardServiceImpl.getInstance());
                break;
            case POST_UNLINK_CREDIT_CARD:
                command = new PostUnlinkCreditCardCommand(CreditCardServiceImpl.getInstance());
                break;
            case GET_CHANGE_PASSWORD_PAGE:
                command = new GetPageCommand("/change-password");
                break;
            case CREATE_CHANGE_PASSWORD_REQUEST:
                command = new PostCreateChangePasswordRequestCommand(
                        PasswordChangeRequestServiceImpl.getInstance(),
                        JavaxEmailSenderImpl.getInstance()
                );
                break;
            case GET_DO_CHANGE_PASSWORD_PAGE:
                command = new GetDoChangePasswordPageCommand(
                        PasswordChangeRequestServiceImpl.getInstance()
                );
                break;
            case POST_CHANGE_PASSWORD:
                command = new PostChangePasswordCommand(
                        PasswordChangeRequestServiceImpl.getInstance()
                );
                break;
            case POST_PAY_BALANCE:
                command = new PostPayBalanceCommand(
                        UserServiceImpl.getInstance(),
                        UserInfoServiceImpl.getInstance()
                );
        }

        return command;
    }
}
