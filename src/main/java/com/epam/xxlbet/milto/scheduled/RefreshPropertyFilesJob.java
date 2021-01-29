package com.epam.xxlbet.milto.scheduled;

import com.epam.xxlbet.milto.utils.PropertyLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_BETS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_MATCHES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_OPPONENTS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_ROLES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_SPORTS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_STATUSES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_TOURNAMENTS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_USERS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_USER_INFO;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.FILE_WITH_QUERIES_FOR_TABLE_VERIFICATION_TOKENS;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.MAIL_PROPERTIES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.MESSAGES_EN_PROPERTIES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.MESSAGES_RU_PROPERTIES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.PROJECT_PROPERTIES;
import static java.util.Arrays.asList;

/**
 * RefreshPropertyFilesJob.
 * Refresh content of .properties files
 * and upload changes to application.
 *
 * @author Aliaksei Milto
 */
public class RefreshPropertyFilesJob implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(RefreshPropertyFilesJob.class);
    private static RefreshPropertyFilesJob instance;
    private PropertyLoader propertyLoader;
    private List<String> files = asList(
            MESSAGES_EN_PROPERTIES, MESSAGES_RU_PROPERTIES, MESSAGES_EN_PROPERTIES,
            MAIL_PROPERTIES, PROJECT_PROPERTIES,
            FILE_WITH_QUERIES_FOR_TABLE_BETS, FILE_WITH_QUERIES_FOR_TABLE_OPPONENTS, FILE_WITH_QUERIES_FOR_TABLE_ROLES,
            FILE_WITH_QUERIES_FOR_TABLE_SPORTS, FILE_WITH_QUERIES_FOR_TABLE_STATUSES, FILE_WITH_QUERIES_FOR_TABLE_TOURNAMENTS,
            FILE_WITH_QUERIES_FOR_TABLE_USERS, FILE_WITH_QUERIES_FOR_TABLE_USER_INFO,
            FILE_WITH_QUERIES_FOR_TABLE_VERIFICATION_TOKENS, FILE_WITH_QUERIES_FOR_TABLE_MATCHES
    );

    private RefreshPropertyFilesJob() {
        this.propertyLoader = PropertyLoader.getInstance();
    }

    public static RefreshPropertyFilesJob getInstance() {
        if (instance == null) {
            instance = new RefreshPropertyFilesJob();
        }

        return instance;
    }

    @Override
    public void run() {
        LOG.debug("Executing RefreshPropertyFilesJob...");

        for (String file : files) {
            try {
                propertyLoader.init(file);
            } catch (IOException e) {
                LOG.error("Error while executing RefreshPropertyFilesJob...", e);
            }
        }

        LOG.debug("Executed RefreshPropertyFilesJob successfully");
    }
}
