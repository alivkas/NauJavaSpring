package ru.matveyelovskikh.naujavaspring.service;

import ru.matveyelovskikh.naujavaspring.dto.NotificationDto;
import ru.matveyelovskikh.naujavaspring.entity.NotificationEntity;

import java.time.LocalDateTime;

/**
 * Интерфейс сервиса уведомлений
 */
public interface NotificationService {

    /**
     * Проверить, необходимо ли уведомить
     * @param date текущая дата со временем
     * @return true - необходимо уведомить, false - нет необходимости
     */
    boolean shouldNotify(LocalDateTime date);

    /**
     * Уведомить о наступлении события
     */
    void eventNotify();

    /**
     * Создать и получить сущность уведомление
     * @param notificationDto дто уведомления
     * @return сущность уведомления
     */
    NotificationEntity createAndGetNotify(NotificationDto notificationDto);
}
