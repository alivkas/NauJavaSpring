package ru.matveyelovskikh.naujavaspring.criteria;

import ru.matveyelovskikh.naujavaspring.entity.UserEntity;

import java.util.Optional;

/**
 * Взаимодействие с UserEntity при помощи Criteria API
 */
public interface UserCriteriaRepository {

    /**
     * Найти пользователя по его имени или по его почте
     * @param username имя пользователя
     * @param email почта пользователя
     * @return сущность пользователя, если она существует
     */
    Optional<UserEntity> findByUsernameOrEmail(String username, String email);
}
