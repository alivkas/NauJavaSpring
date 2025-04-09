package ru.matveyelovskikh.naujavaspring.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.matveyelovskikh.naujavaspring.entity.NotificationEntity;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;
import ru.matveyelovskikh.naujavaspring.exception.UserNotFoundException;
import ru.matveyelovskikh.naujavaspring.repository.NotificationCrud;
import ru.matveyelovskikh.naujavaspring.repository.UserCrud;

import java.util.List;

/**
 * Контроллер кастомного запроса Notification
 */
@RestController
@RequestMapping("/custom/notification")
public class NotificationCustomController {

    private final NotificationCrud notificationCrud;
    private final UserCrud userCrud;

    /**
     * Внедрение зависимостей NotificationCrud, UserCrud
     * @param notificationCrud crud уведомления
     * @param userCrud crud пользователя
     */
    @Autowired
    public NotificationCustomController(NotificationCrud notificationCrud,
                                        UserCrud userCrud) {
        this.notificationCrud = notificationCrud;
        this.userCrud = userCrud;
    }

    /**
     * GET запрос на получение всех уведомлений пользователя
     * @param userId id пользователя
     * @return список всех уведомлений пользователя
     */
    @GetMapping(path = "/all-by-user/{userId}")
    public ResponseEntity<?> getAllNotificationsByUser(@PathVariable Long userId) {
        UserEntity user = userCrud.findById(userId).orElseThrow(()
                -> new UserNotFoundException(userId));
        List<NotificationEntity> notifications = notificationCrud.findAllByUser(user);

        return ResponseEntity.ok().body(notifications);
    }
}
