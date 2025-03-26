package ru.matveyelovskikh.naujavaspring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.matveyelovskikh.naujavaspring.entity.NotificationEntity;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;

import java.util.List;

/**
 * CRUD интерфейс NotificationEntity
 */
public interface NotificationCrud extends CrudRepository<NotificationEntity, Long> {

    /**
     * Найти все уведомления пользователя
     * @param user пользователь
     * @return список уведомлений
     */
    @Query("SELECT notify FROM NotificationEntity notify WHERE notify.user = :user")
    List<NotificationEntity> findAllByUser(@Param("user") UserEntity user);
}
