package ru.matveyelovskikh.naujavaspring.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.matveyelovskikh.naujavaspring.dto.EventCategoryDto;
import ru.matveyelovskikh.naujavaspring.entity.EventCategoryEntity;

/**
 * Маппер категории события
 */
@Mapper(componentModel = "spring")
public interface EventCategoryMapper {

    /**
     * Преобразование дто в сущность
     * @param dto дто категории события
     * @return сущность категории события
     */
    @Mapping(target = "visible", constant = "true")
    @Mapping(target = "default", constant = "true")
    @Mapping(target = "archived", constant = "false")
    EventCategoryEntity toEntity(EventCategoryDto dto);
}
