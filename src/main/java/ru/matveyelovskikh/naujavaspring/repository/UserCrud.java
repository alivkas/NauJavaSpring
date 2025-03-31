package ru.matveyelovskikh.naujavaspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;

/**
 * CRUD интерфейс UserEntity
 */
@RepositoryRestResource(path = "user")
public interface UserCrud extends CrudRepository<UserEntity, Long> {

    /**
     * Узнать существует ли пользователь по его имени и почте
     * @param username имя пользователя
     * @param email почта пользователя
     * @return true - есть в бд, false - нет в бд
     */
    boolean existsByUsernameAndEmail(String username, String email);
}
