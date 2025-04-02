package ru.matveyelovskikh.naujavaspring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matveyelovskikh.naujavaspring.dto.EventCategoryDto;
import ru.matveyelovskikh.naujavaspring.entity.EventCategoryEntity;
import ru.matveyelovskikh.naujavaspring.mapstruct.EventCategoryMapper;
import ru.matveyelovskikh.naujavaspring.repository.EventCategoryCrud;
import ru.matveyelovskikh.naujavaspring.service.EventCategoryService;

/**
 * Реализация сервиса категории событий
 */
@Service
public class EventCategoryServiceImpl implements EventCategoryService {

    private final EventCategoryCrud eventCategoryCrud;
    private final EventCategoryMapper eventCategoryMapper;

    /**
     * Внедрение зависимостей EventCategoryCrud, EventCategoryMapper
     * @param eventCategoryCrud crud категории событий
     * @param eventCategoryMapper маппер категорий
     */
    @Autowired
    public EventCategoryServiceImpl(EventCategoryCrud eventCategoryCrud,
                                    EventCategoryMapper eventCategoryMapper) {
        this.eventCategoryCrud = eventCategoryCrud;
        this.eventCategoryMapper = eventCategoryMapper;
    }

    @Transactional
    @Override
    public EventCategoryEntity getOrCreateEventCategory(EventCategoryDto eventCategoryDto) {
        return eventCategoryCrud.findByName(eventCategoryDto.name())
                .orElseGet(() -> {
                    EventCategoryEntity eventCategory = eventCategoryMapper.toEntity(eventCategoryDto);
                    return eventCategoryCrud.save(eventCategory);
                });
    }
}
