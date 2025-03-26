package ru.matveyelovskikh.naujavaspring.criteria;

import ru.matveyelovskikh.naujavaspring.entity.NotificationEntity;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;

import java.util.List;

/**
 * Взаимодействие с NotificationEntity при помощи Criteria API
 */
public interface NotificationCriteriaRepository {

    /**
     * Найти все уведомления пользователя
     * @param user пользователь
     * @return список уведомлений
     */
    List<NotificationEntity> findAllByUser(UserEntity user);
}
