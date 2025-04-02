package ru.matveyelovskikh.naujavaspring.entity;

import jakarta.persistence.*;
import ru.matveyelovskikh.naujavaspring.entity.base.BasicEntity;

import java.time.LocalDateTime;

/**
 * Сущность уведомлений
 */
@Entity
@Table(name = "tbl_notification")
public class NotificationEntity extends BasicEntity {

    @Column(name = "message")
    private String message;
    @Column(name = "notification_time")
    private LocalDateTime notificationTime;
    @Column(name = "is_sent")
    private Boolean isSent;
    @Column(name = "is_read")
    private Boolean isRead;
    @Column(name = "is_archived")
    private Boolean isArchived;

    @ManyToOne
    @JoinColumn(name = "events_day_id")
    private EventsDayEntity eventsDay;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public NotificationEntity() {
    }

    /**
     * Конструктор NotificationEntity для инициализации полей
     * @param message сообщение уведомления
     * @param notificationTime время уведомления
     * @param isSent отправлено ли уведомление
     * @param isRead прочитано ли уведомление
     * @param isArchived архивировано ли уведомление
     * @param eventsDay событие дня уведомления
     * @param user пользователя уведомления
     */
    public NotificationEntity(String message,
                              LocalDateTime notificationTime,
                              Boolean isSent,
                              Boolean isRead,
                              Boolean isArchived,
                              EventsDayEntity eventsDay,
                              UserEntity user) {
        this.message = message;
        this.notificationTime = notificationTime;
        this.isSent = isSent;
        this.isRead = isRead;
        this.isArchived = isArchived;
        this.eventsDay = eventsDay;
        this.user = user;
    }

    /**
     * Получить сообщение уведомления
     * @return сообщение уведомления
     */
    public String getMessage() {
        return message;
    }

    /**
     * Установить сообщение уведомления
     * @param message сообщение уведомления
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Получить время уведомления
     * @return время уведомления
     */
    public LocalDateTime getNotificationTime() {
        return notificationTime;
    }

    /**
     * Установить время уведомления
     * @param notificationTime время уведомления
     */
    public void setNotificationTime(LocalDateTime notificationTime) {
        this.notificationTime = notificationTime;
    }

    /**
     * Получить статус отправки уведомления
     * @return статус отправки уведомления
     */
    public Boolean getSent() {
        return isSent;
    }

    /**
     * Установить статус отправки уведомления
     * @param sent статус отправки уведомления
     */
    public void setSent(Boolean sent) {
        isSent = sent;
    }

    /**
     * Получить статус просмотра уведомления
     * @return статус просмотра уведомления
     */
    public Boolean getRead() {
        return isRead;
    }

    /**
     * Установить статус просмотра уведомления
     * @param read статус просмотра уведомления
     */
    public void setRead(Boolean read) {
        isRead = read;
    }

    /**
     * Получить статус архива уведомления
     * @return статус архива уведомления
     */
    public Boolean getArchived() {
        return isArchived;
    }

    /**
     * Установить статус архива уведомления
     * @param archived статус архива уведомления
     */
    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    /**
     * Получить события дня уведомления
     * @return события дня уведомления
     */
    public EventsDayEntity getEventsDay() {
        return eventsDay;
    }

    /**
     * Установить события дня уведомления
     * @param eventsDay события дня уведомления
     */
    public void setEventsDay(EventsDayEntity eventsDay) {
        this.eventsDay = eventsDay;
    }

    /**
     * Получить пользователя уведомления
     * @return пользователь уведомления
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Установить пользователя уведомления
     * @param user пользователь уведомления
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }
}
