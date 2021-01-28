package com.epam.xxlbet.milto.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * DateUtils.
 *
 * @author Aliaksei Milto
 */
public final class DateUtils {
    private DateUtils() {}

    public static String formatLocalDateTime(final LocalDateTime localDateTime, final String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
}