package ru.matveyelovskikh.naujavaspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.matveyelovskikh.naujavaspring.entity.EventCategoryEntity;

import java.util.Optional;

/**
 * CRUD интерфейс EventCategoryEntity
 */
@RepositoryRestResource(path = "event-category")
public interface EventCategoryCrud extends CrudRepository<EventCategoryEntity, Long> {

    /**
     * Найти категорию по имени
     * @param name имя категории
     * @return событие, если оно есть
     */
    Optional<EventCategoryEntity> findByName(String name);
}
