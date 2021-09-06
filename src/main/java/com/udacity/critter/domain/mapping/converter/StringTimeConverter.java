package com.udacity.critter.domain.mapping.converter;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class StringTimeConverter extends AbstractConverter<String, LocalTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.US);

    public static LocalTime parse(String s) {
        return LocalTime.parse(s, formatter);
    }

    @Override
    protected LocalTime convert(String s) {
        return LocalTime.parse(s, formatter);
    }
}
