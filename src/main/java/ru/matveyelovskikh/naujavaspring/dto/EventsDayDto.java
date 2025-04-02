package ru.matveyelovskikh.naujavaspring.dto;


import java.time.LocalDateTime;

/**
 * ДТО дневных событий
 * @param calendar календарь
 * @param message сообщение события
 * @param userId id пользователя
 * @param eventCategoryDto дто категории события
 * @param locationDto дто места проведения события
 */
public record EventsDayDto(LocalDateTime calendar,
                           String message,
                           Long userId,
                           EventCategoryDto eventCategoryDto,
                           LocationDto locationDto) {
}
