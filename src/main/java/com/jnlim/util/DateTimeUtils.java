package com.jnlim.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

    public static LocalDateTime parseToLocalDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, formatter);
    }
}
