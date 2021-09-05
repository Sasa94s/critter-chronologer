package com.udacity.critter.domain.mapping.converter;

import org.modelmapper.AbstractConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Locale;

public class TimeStringConverter extends AbstractConverter<LocalTime, String> {
    private static final DateFormat dateFormat = new SimpleDateFormat("hh:mm a", Locale.US);

    public static String format(LocalTime time) {
        return dateFormat.format(time);
    }

    @Override
    protected String convert(LocalTime time) {
        return dateFormat.format(time);
    }
}
