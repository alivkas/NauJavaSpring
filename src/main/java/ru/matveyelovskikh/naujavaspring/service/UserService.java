package ru.matveyelovskikh.naujavaspring.service;

import ru.matveyelovskikh.naujavaspring.dto.UserDto;

/**
 * Интерфейс бизнес логики пользователя
 */
public interface UserService {

    /**
     * Создать пользователя через дто
     * @param userDto дто пользователя
     */
    void createUser(UserDto userDto);
}
