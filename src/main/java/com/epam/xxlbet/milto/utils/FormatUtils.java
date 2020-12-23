package com.epam.xxlbet.milto.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * FormatUtils.
 *
 * @author Aliaksei Milto
 */
public final class FormatUtils {
    private FormatUtils() {}

    public static String formatLocalDateTime(final LocalDateTime localDateTime, final String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}