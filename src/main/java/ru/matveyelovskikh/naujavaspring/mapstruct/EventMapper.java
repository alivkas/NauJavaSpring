package ru.matveyelovskikh.naujavaspring.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.matveyelovskikh.naujavaspring.dto.EventsDayDto;
import ru.matveyelovskikh.naujavaspring.entity.EventCategoryEntity;
import ru.matveyelovskikh.naujavaspring.entity.EventsDayEntity;
import ru.matveyelovskikh.naujavaspring.entity.LocationEntity;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;

import java.util.List;

/**
 * Маппер события
 */
@Mapper(componentModel = "spring",
        uses = {EventCategoryMapper.class, LocationMapper.class})
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

    /**
     * Маппинг в дто
     * @param entity сущность
     * @return дто
     */
    @Mapping(target = "eventCategoryDto", source = "eventCategory")
    @Mapping(target = "locationDto", source = "location")
    @Mapping(target = "userId", source = "user.id")
    EventsDayDto toDto(EventsDayEntity entity);

    /**
     * Преобразовать список сущностей в список дто
     * @param entities список сущностей
     * @return список дто
     */
    List<EventsDayDto> toDtoList(List<EventsDayEntity> entities);
}
