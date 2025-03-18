package ru.matveyelovskikh.naujavaspring.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Обработка ввода данных
 */
public class InputProcessingUtils {

    /**
     * Получить отформатированную дату со временем
     * @param textDate дата
     * @param textTime время
     * @return дата со временем
     */
    public LocalDateTime getFormattedLocalDateTime(String textDate, String textTime) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(textDate, dateFormatter);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm");
        LocalTime time = LocalTime.parse(textTime, timeFormatter);

        return LocalDateTime.of(date, time);
    }
}
