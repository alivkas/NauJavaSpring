package ru.matveyelovskikh.naujavaspring.dto;


import java.time.LocalDateTime;

/**
 * ДТО дневных событий
 * @param calendar календарь
 * @param message сообщение события
 */
public record EventsDayDto(LocalDateTime calendar,
                           String message) {
}
