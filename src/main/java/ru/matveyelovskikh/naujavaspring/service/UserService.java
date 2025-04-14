package ru.matveyelovskikh.naujavaspring.service;

import ru.matveyelovskikh.naujavaspring.dto.UserDto;

import java.util.List;

/**
 * Интерфейс бизнес логики пользователя
 */
public interface UserService {

    /**
     * Создать пользователя через дто
     * @param userDto дто пользователя
     */
    void createUser(UserDto userDto);

    /**
     * Получить всех пользователей
     * @return список дто пользователей
     */
    List<UserDto> getAllUsers();

    /**
     * Проверить существует ли пользователь
     * @param username имя пользователя
     * @param email почта пользователя
     * @return true - если существует, false - не существует
     */
    Boolean isUserExists(String username, String email);
}
