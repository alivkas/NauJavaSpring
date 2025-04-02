package ru.matveyelovskikh.naujavaspring.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.matveyelovskikh.naujavaspring.dto.LocationDto;
import ru.matveyelovskikh.naujavaspring.entity.LocationEntity;

/**
 * Маппер локации
 */
@Mapper(componentModel = "spring")
public interface LocationMapper {

    /**
     * Преобразование дто в сущность
     * @param dto дто локации
     * @return сущность локации
     */
    @Mapping(target = "archived", constant = "false")
    LocationEntity toEntity(LocationDto dto);
}
