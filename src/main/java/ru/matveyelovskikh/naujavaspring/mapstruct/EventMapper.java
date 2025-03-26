package ru.matveyelovskikh.naujavaspring.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.matveyelovskikh.naujavaspring.dto.EventsDayDto;
import ru.matveyelovskikh.naujavaspring.entity.EventCategoryEntity;
import ru.matveyelovskikh.naujavaspring.entity.EventsDayEntity;
import ru.matveyelovskikh.naujavaspring.entity.LocationEntity;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;

/**
 * Маппер события
 */
@Mapper(componentModel = "spring")
public interface EventMapper {

    /**
     * Обновление сущности из дто
     * @param dto дто
     * @param entity сущность
     */
    void updateFromDto(EventsDayDto dto, @MappingTarget EventsDayEntity entity);

    /**
     * Преобразование дто в сущность
     * @param dto дто события
     * @param user crud пользователя
     * @param eventCategory crud категории события
     * @param location crud локации
     * @return сущность EventsDay
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "eventCategory", source = "eventCategory")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "eventStatus", constant = "WAITING")
    @Mapping(target = "notification", ignore = true)
    EventsDayEntity toEntity(EventsDayDto dto,
                             UserEntity user,
                             EventCategoryEntity eventCategory,
                             LocationEntity location);
}
