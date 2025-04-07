package ru.matveyelovskikh.naujavaspring.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.matveyelovskikh.naujavaspring.dto.NotificationDto;
import ru.matveyelovskikh.naujavaspring.service.NotificationService;

import java.util.List;

/**
 * Контроллер кастомного запроса Notification
 */
@RestController
@RequestMapping("/custom/notification")
public class NotificationCustomController {

    private final NotificationService notificationService;

    /**
     * Внедрение зависимостей NotificationService
     * @param notificationService сервис уведомлений
     */
    @Autowired
    public NotificationCustomController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * GET запрос на получение всех уведомлений пользователя
     * @param userId id пользователя
     * @return список всех уведомлений пользователя
     */
    @GetMapping(path = "/all-by-user/{userId}")
    public ResponseEntity<List<NotificationDto>> getAllNotificationsByUser(@PathVariable Long userId) {
        List<NotificationDto> notifications = notificationService
                .getAllNotificationsByUser(userId);
        return ResponseEntity.ok().body(notifications);
    }
}
