package com.bank.sistemabancario.web.controller.GUI;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        LocalDate date = LocalDate.parse(source);
        return LocalDateTime.of(date, LocalTime.MIDNIGHT);
    }
}
