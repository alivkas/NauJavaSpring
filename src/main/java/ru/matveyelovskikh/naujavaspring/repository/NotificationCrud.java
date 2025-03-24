package ru.matveyelovskikh.naujavaspring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.matveyelovskikh.naujavaspring.entity.NotificationEntity;

/**
 * CRUD интерфейс NotificationEntity
 */
public interface NotificationCrud extends CrudRepository<NotificationEntity, Long> {
}
