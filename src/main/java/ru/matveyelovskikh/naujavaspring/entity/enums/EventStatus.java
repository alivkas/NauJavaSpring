package ru.matveyelovskikh.naujavaspring.entity.enums;

/**
 * Статус события
 */
public enum EventStatus {
    /**
     * Событие в ожидании даты
     */
    WAITING,
    /**
     * Событие активно
     */
    ACTIVE,
    /**
     * Событие закрыто
     */
    CLOSED
}
