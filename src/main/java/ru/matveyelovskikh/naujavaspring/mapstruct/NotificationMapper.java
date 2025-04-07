package ru.matveyelovskikh.naujavaspring.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.matveyelovskikh.naujavaspring.dto.NotificationDto;
import ru.matveyelovskikh.naujavaspring.entity.EventsDayEntity;
import ru.matveyelovskikh.naujavaspring.entity.NotificationEntity;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;

import java.util.List;

/**
 * Маппер уведомления
 */
@Mapper(componentModel = "spring")
public interface NotificationMapper {

    /**
     * Преобразование дто в сущность
     * @param dto дто уведомлений
     * @param user пользователь уведомления
     * @param eventsDay событие дня уведомления
     * @return сущность Notification
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eventsDay", source = "eventsDay")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "message", source = "dto.message")
    @Mapping(target = "notificationTime", source = "dto.notificationTime")
    @Mapping(target = "sent", constant = "true")
    @Mapping(target = "read", constant = "false")
    @Mapping(target = "archived", constant = "false")
    NotificationEntity toEntity(NotificationDto dto,
                                UserEntity user,
                                EventsDayEntity eventsDay);

    /**
     * Преобразование сущности в дто
     * @param entity сущность
     * @return дто
     */
    @Mapping(target = "eventsDayId", source = "eventsDay.id")
    @Mapping(target = "userId", source = "user.id")
    NotificationDto toDto(NotificationEntity entity);

    /**
     * Преобразовать список сущностей в список дто
     * @param entities список сущностей
     * @return список дто
     */
    List<NotificationDto> toDtoList(List<NotificationEntity> entities);
}
