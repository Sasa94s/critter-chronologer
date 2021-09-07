package com.udacity.critter.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class FakeUtil {
    static final FakeValuesService service = new FakeValuesService(Locale.US, new RandomService());

    static String generateEmail() {
        return service.bothify("????##@gmail.com");
    }

    static String generatePhoneNumber() {
        return service.numerify("###-####-###");
    }

    static Name generateName() {
        return new Faker(new Random(24)).name();
    }

    static Date generateBirthDate() {
        return new Faker(new Random(24)).date().birthday();
    }

    static String generateTime() {
        return String.format("%02d:%02d %s",
                ThreadLocalRandom.current().nextInt(13),
                ThreadLocalRandom.current().nextInt(60),
                ThreadLocalRandom.current().nextBoolean() ? "AM" : "PM");
    }

    static LocalDate generateDate() {
        return new Faker().date().past(ThreadLocalRandom.current().nextInt(365), TimeUnit.DAYS)
                .toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
    }

    static String generateAddress() {
        return new Faker().address().fullAddress();
    }

    static String generateCity() {
        return new Faker().address().cityName();
    }
}
