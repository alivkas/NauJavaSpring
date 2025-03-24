package ru.matveyelovskikh.naujavaspring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.matveyelovskikh.naujavaspring.entity.EventCategoryEntity;

/**
 * CRUD интерфейс EventCategoryEntity
 */
public interface EventCategoryCrud extends CrudRepository<EventCategoryEntity, Long> {
}
