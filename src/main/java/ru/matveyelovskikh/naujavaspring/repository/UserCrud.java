package ru.matveyelovskikh.naujavaspring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;

import java.util.Optional;

/**
 * CRUD интерфейс UserEntity
 */
public interface UserCrud extends CrudRepository<UserEntity, Long> {

    /**
     * Найти пользователя по его имени или по его почте
     * @param username имя пользователя
     * @param email почта пользователя
     * @return сущность пользователя, если она существует
     */
    Optional<UserEntity> findByUsernameOrEmail(String username, String email);
}
