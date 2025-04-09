package ru.matveyelovskikh.naujavaspring.exception;

/**
 * Исключение, которое вызывается, когда пользователь не найден
 */
public class UserNotFoundException extends RuntimeException {

    public final static Integer CODE = 404;

    /**
     * Сообщение исключения
     * @param id id пользователя
     */
    public UserNotFoundException(Long id) {
        super("Пользователя с id " + id + " не найдено");
    }

    /**
     * Сообщение исключения
     * @param username имя пользователя
     */
    public UserNotFoundException(String username) {
        super("Пользователя с именем " + username + " не найдено");
    }
}
