package ru.matveyelovskikh.naujavaspring.service;

import ru.matveyelovskikh.naujavaspring.dto.EventsDayDto;
import ru.matveyelovskikh.naujavaspring.entity.EventsDayEntity;

import java.util.Map;

/**
 * Интерфейс бизнес логики дневных событий
 */
public interface EventsDayService {

    /**
     * Получить событие по id
     * @param id id события
     * @return событие дня
     */
    EventsDayEntity getEventById(Long id);

    /**
     * Получить все события
     * @return список событий
     */
    Map<Long, EventsDayEntity> getAllEvents();

    /**
     * Создать событие
     * @param eventsDay дто события
     */
    void createEventDay(EventsDayDto eventsDay);

    /**
     * Удалить событие по id
     * @param id id события
     */
    void deleteById(Long id);

    /**
     * Обновить событие по id
     * @param id id события
     * @param eventsDay ДТО события
     */
    void updateById(Long id, EventsDayDto eventsDay);

    /**
     * Принять событие по id
     * @param id id
     */
    void acceptById(Long id);
}