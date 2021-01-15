package com.epam.xxlbet.milto.domain;

/**
 * Result of email registration confirmation:
 * success, if successfully confirmed registration
 * expired, if did not confirm registration because verification token expired
 * invalid, if did not confirm registration because verification token is invalidю
 *
 * @author Aliaksei Milto
 */
public enum ConfirmationResult {
    SUCCESS, EXPIRED, INVALID
}
