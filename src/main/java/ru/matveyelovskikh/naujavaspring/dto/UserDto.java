package ru.matveyelovskikh.naujavaspring.dto;

import java.util.List;

/**
 * ДТО пользователя
 * @param username имя пользователя
 * @param password пароль
 * @param email почта пользователя
 * @param id id пользователя
 * @param admin админ ли пользователь
 * @param eventsDayDtos список событий дня пользователя
 */
public record UserDto(Long id,
                      String username,
                      String password,
                      String email,
                      Boolean admin,
                      List<EventsDayDto> eventsDayDtos) {
}
