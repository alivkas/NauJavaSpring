package ru.matveyelovskikh.naujavaspring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.matveyelovskikh.naujavaspring.entity.LocationEntity;

/**
 * CRUD интерфейс LocationEntity
 */
public interface LocationCrud extends CrudRepository<LocationEntity, Long> {
}
