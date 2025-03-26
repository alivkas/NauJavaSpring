package ru.matveyelovskikh.naujavaspring.service;

import ru.matveyelovskikh.naujavaspring.dto.LocationDto;
import ru.matveyelovskikh.naujavaspring.entity.LocationEntity;

import java.util.Optional;

/**
 * Сервис локации
 */
public interface LocationService {

    /**
     * Получить или если сущности нет, создать сущность
     * @param locationDto дто локации
     * @return локация
     */
    LocationEntity getOrCreateCategory(LocationDto locationDto);
}
