package ru.matveyelovskikh.naujavaspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.matveyelovskikh.naujavaspring.entity.LocationEntity;

import java.util.Optional;

/**
 * CRUD интерфейс LocationEntity
 */
@RepositoryRestResource(path = "location")
public interface LocationCrud extends CrudRepository<LocationEntity, Long> {

    /**
     * Найти сущность по названию места
     * @param place название места
     * @return сущность, если есть, либо null
     */
    Optional<LocationEntity> findByPlace(String place);
}
