package com.epam.xxlbet.milto.domain;

/**
 * Result of email registration confirmation: success, expired token or invalid token.
 *
 * @author Aliaksei Milto
 */
public enum ConfirmationResult {
    SUCCESS, EXPIRED, INVALID
}
