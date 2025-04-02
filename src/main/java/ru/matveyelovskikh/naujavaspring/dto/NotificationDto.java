package ru.matveyelovskikh.naujavaspring.dto;


import java.time.LocalDateTime;

/**
 * ДТО уведомления
 * @param message сообщение уведомления
 * @param eventsDayId id дня события
 * @param userId id пользователя
 * @param notificationTime время события
 */
public record NotificationDto(String message,
                              LocalDateTime notificationTime,
                              Long eventsDayId,
                              Long userId) {
}
