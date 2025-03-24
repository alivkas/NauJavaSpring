package ru.matveyelovskikh.naujavaspring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.matveyelovskikh.naujavaspring.entity.EventsDayEntity;

/**
 * CRUD интерфейс EventsDayEntity
 */
public interface EventsDayCrud extends CrudRepository<EventsDayEntity, Long> {
}
