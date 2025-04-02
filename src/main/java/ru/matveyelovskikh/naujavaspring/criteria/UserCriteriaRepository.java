package ru.matveyelovskikh.naujavaspring.criteria;

import ru.matveyelovskikh.naujavaspring.entity.UserEntity;

import java.util.Optional;

/**
 * Взаимодействие с UserEntity при помощи Criteria API
 */
public interface UserCriteriaRepository {

    /**
     * Узнать существует ли пользователь по его имени и почте
     * @param username имя пользователя
     * @param email почта пользователя
     * @return true - есть в бд, false - нет в бд
     */
    boolean existsByUsernameAndEmail(String username, String email);

    /**
     * Найти пользователя по id
     * @param id id пользователя
     * @return пользователя, если он есть
     */
    Optional<UserEntity> findById(Long id);
}
