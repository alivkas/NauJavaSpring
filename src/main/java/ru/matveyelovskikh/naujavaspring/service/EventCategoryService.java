package ru.matveyelovskikh.naujavaspring.service;

import ru.matveyelovskikh.naujavaspring.dto.EventCategoryDto;
import ru.matveyelovskikh.naujavaspring.entity.EventCategoryEntity;

/**
 * Сервис категории событий
 */
public interface EventCategoryService {

    /**
     * Создать категорию события ил получить существующую
     * @param eventCategoryDto дто категории события
     */
    EventCategoryEntity getOrCreateEventCategory(EventCategoryDto eventCategoryDto);
}
