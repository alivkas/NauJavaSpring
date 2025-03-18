package ru.matveyelovskikh.naujavaspring.entity;

import ru.matveyelovskikh.naujavaspring.entity.enums.EventStatus;

import java.time.LocalDateTime;

/**
 * Сущность события дня
 */
public class EventsDayEntity {

    private Long id;
    private LocalDateTime calendar;
    private String message;
    private EventStatus eventStatus;

    /**
     * Пустой конструктор для инициализации
     */
    public EventsDayEntity() {
    }

    /**
     * Конструктор для создания сущности
     * @param id id
     * @param calendar календарь
     * @param message сообщение
     * @param eventStatus статус
     */
    public EventsDayEntity(Long id,
                           LocalDateTime calendar,
                           String message,
                           EventStatus eventStatus) {
        this.id = id;
        this.calendar = calendar;
        this.message = message;
        this.eventStatus = eventStatus;
    }

    /**
     * Получить id
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Установить id
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Событие " + "\n" +
                "ID: " + id + "\n" +
                "Дата события: " + calendar + "\n" +
                "Сообщение: " + message + "\n" +
                "Статус напоминания: " + eventStatus + "\n" +
                "=============================================\n";
    }
}
