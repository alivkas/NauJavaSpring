package ru.matveyelovskikh.naujavaspring.service;

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
}
