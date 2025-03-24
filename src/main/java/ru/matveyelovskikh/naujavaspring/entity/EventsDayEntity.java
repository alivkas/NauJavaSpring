package ru.matveyelovskikh.naujavaspring.entity;

import jakarta.persistence.*;
import ru.matveyelovskikh.naujavaspring.entity.base.BasicEntity;
import ru.matveyelovskikh.naujavaspring.entity.enums.EventStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сущность события дня
 */
@Entity
@Table(name = "tbl_events_day")
public class EventsDayEntity extends BasicEntity {

    @Column(name = "calendar")
    private LocalDateTime calendar;
    @Column(name = "message")
    private String message;
    @Column(name = "event_status")
    private EventStatus eventStatus;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "event_category_id", nullable = false)
    private EventCategoryEntity eventCategory;
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity location;

    @OneToMany(mappedBy = "eventsDay", cascade = CascadeType.ALL)
    private List<NotificationEntity> notification;

    /**
     * Пустой конструктор для инициализации
     */
    public EventsDayEntity() {
    }

    /**
     * Конструктор для создания сущности
     * @param calendar календарь
     * @param message сообщение
     * @param eventStatus статус
     */
    public EventsDayEntity(LocalDateTime calendar,
                           String message,
                           EventStatus eventStatus) {
        this.calendar = calendar;
        this.message = message;
        this.eventStatus = eventStatus;
    }

    /**
     * Получить календарь
     * @return календарь
     */
    public LocalDateTime getCalendar() {
        return calendar;
    }

    /**
     * Установить календарь
     * @param calendar календарь
     */
    public void setCalendar(LocalDateTime calendar) {
        this.calendar = calendar;
    }

    /**
     * Получить сообщение события
     * @return сообщение
     */
    public String getMessage() {
        return message;
    }

    /**
     * Установить сообщение события
     * @param message сообщение
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Получить статус события
     * @return статус события
     */
    public EventStatus getEventStatus() {
        return eventStatus;
    }

    /**
     * Установить статус сообщения
     * @param eventStatus статус сообщения
     */
    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    /**
     * Получить пользователя события дня
     * @return пользователь события дня
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Установить пользователя события дня
     * @param user пользователь события дня
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * Получить категорию события дня
     * @return категория события дня
     */
    public EventCategoryEntity getEventCategory() {
        return eventCategory;
    }

    /**
     * Установить категорию события дня
     * @param eventCategory категория события дня
     */
    public void setEventCategory(EventCategoryEntity eventCategory) {
        this.eventCategory = eventCategory;
    }

    /**
     * Получить место события дня
     * @return место события дня
     */
    public LocationEntity getLocation() {
        return location;
    }

    /**
     * Установить место события дня
     * @param location место события дня
     */
    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    /**
     * Получить список уведомлений события дня
     * @return список уведомлений события дня
     */
    public List<NotificationEntity> getNotification() {
        return notification;
    }

    /**
     * Установить список уведомлений события дня
     * @param notification список уведомлений события дня
     */
    public void setNotification(List<NotificationEntity> notification) {
        this.notification = notification;
    }

    @Override
    public String toString() {
        return "Событие " + "\n" +
                "Дата события: " + calendar + "\n" +
                "Сообщение: " + message + "\n" +
                "Статус напоминания: " + eventStatus + "\n" +
                "=============================================\n";
    }
}
