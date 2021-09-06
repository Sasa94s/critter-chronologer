package com.udacity.critter.domain.mapping.converter;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class TimeStringConverter extends AbstractConverter<LocalTime, String> {
    private static final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.US);

    public static String format(LocalTime time) {
        return dtFormatter.format(time);
    }

    @Override
    protected String convert(LocalTime time) {
        return dtFormatter.format(time);
    }
}
