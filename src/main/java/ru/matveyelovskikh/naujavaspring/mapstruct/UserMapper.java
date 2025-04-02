package ru.matveyelovskikh.naujavaspring.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.matveyelovskikh.naujavaspring.dto.UserDto;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;

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
}
