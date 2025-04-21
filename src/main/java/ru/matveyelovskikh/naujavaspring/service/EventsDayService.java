package ru.matveyelovskikh.naujavaspring.service;

import ru.matveyelovskikh.naujavaspring.dto.EventsDayDto;
import ru.matveyelovskikh.naujavaspring.entity.EventsDayEntity;

import java.util.List;

/**
 * Интерфейс бизнес логики дневных событий
 */
public interface EventsDayService {

    /**
     * Получить событие по id
     * @param id id события
     * @return событие дня
     */
    EventsDayDto getEventById(Long id);

    /**
     * Получить дто всех событий для контроллера
     * @return список дто событий
     */
    List<EventsDayDto> getAllEventsApi();

    /**
     * Получить всех события для внутреннего использования
     * @return список событий
     */
    List<EventsDayEntity> getAllEventsService();

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