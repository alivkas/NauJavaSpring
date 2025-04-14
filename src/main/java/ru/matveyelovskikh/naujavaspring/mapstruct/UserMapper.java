package ru.matveyelovskikh.naujavaspring.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.matveyelovskikh.naujavaspring.dto.UserDto;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;

import java.util.List;

/**
 * Маппер пользователя
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Преобразование дто в сущность
     * @param dto дто пользователя
     * @return сущность пользователя
     */
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "emailVerified", constant = "false")
    @Mapping(target = "admin", constant = "false")
    UserEntity toEntity(UserDto dto);

    /**
     * Преобразовать сущность в дто
     * @param entity сущность
     * @return дто
     */
    @Mapping(target = "eventsDayDtos", source = "eventsDay")
    UserDto toDto(UserEntity entity);

    /**
     * Преобразовать список сущностей в список дто
     * @param entities список сущностей
     * @return список дто
     */
    List<UserDto> toDtoList(List<UserEntity> entities);
}
