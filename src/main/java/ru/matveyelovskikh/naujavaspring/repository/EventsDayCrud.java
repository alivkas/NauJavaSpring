package ru.matveyelovskikh.naujavaspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.matveyelovskikh.naujavaspring.entity.EventsDayEntity;

/**
 * CRUD интерфейс EventsDayEntity
 */
@RepositoryRestResource(path = "events-day")
public interface EventsDayCrud extends CrudRepository<EventsDayEntity, Long> {
}
