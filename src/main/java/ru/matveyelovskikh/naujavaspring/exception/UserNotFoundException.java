package ru.matveyelovskikh.naujavaspring.exception;

/**
 * Исключение, которое вызывается, когда пользователь не найден
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Сообщение исключения
     * @param id id пользователя
     */
    public UserNotFoundException(Long id) {
        super("Пользователя с id " + id + " не найдено");
    }
}
