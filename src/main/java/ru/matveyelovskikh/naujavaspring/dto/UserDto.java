package ru.matveyelovskikh.naujavaspring.dto;

/**
 * ДТО пользователя
 * @param username имя пользователя
 * @param password пароль
 * @param email почта пользователя
 */
public record UserDto(String username,
                      String password,
                      String email) {
}
