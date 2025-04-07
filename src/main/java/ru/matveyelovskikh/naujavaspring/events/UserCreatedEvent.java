package ru.matveyelovskikh.naujavaspring.events;

import ru.matveyelovskikh.naujavaspring.entity.UserEntity;

/**
 * Событие создания пользователя
 * @param user пользователь
 */
public record UserCreatedEvent(UserEntity user) {
}
