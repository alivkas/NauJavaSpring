package ru.matveyelovskikh.naujavaspring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.matveyelovskikh.naujavaspring.entity.LocationEntity;

import java.util.Optional;

/**
 * CRUD интерфейс LocationEntity
 */
public interface LocationCrud extends CrudRepository<LocationEntity, Long> {

    /**
     * Найти сущность по названию места
     * @param place название места
     * @return сущность, если есть, либо null
     */
    Optional<LocationEntity> findByPlace(String place);
}
