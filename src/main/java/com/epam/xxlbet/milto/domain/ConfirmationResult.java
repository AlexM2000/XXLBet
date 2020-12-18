package com.epam.xxlbet.milto.domain;

/**
 * Result of email registration confirmation: success, expired token or invalid token
 *
 * @author alexm2000
 */
public enum ConfirmationResult {
    SUCCESS, EXPIRED, INVALID
}
