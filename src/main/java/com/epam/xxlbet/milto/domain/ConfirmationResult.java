package com.epam.xxlbet.milto.domain;

/**
 * Result of email registration confirmation:
 * {@link #SUCCESS}, if successfully confirmed registration
 * {@link #EXPIRED}, if did not confirm registration because verification token expired
 * {@link #INVALID}, if did not confirm registration because verification token is invalidю
 *
 * @author Aliaksei Milto
 */
public enum ConfirmationResult {
    SUCCESS, EXPIRED, INVALID
}
