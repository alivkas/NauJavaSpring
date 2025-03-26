package ru.matveyelovskikh.naujavaspring.exception;

/**
 * Исключение, которое возникает, когда пользователь уже существует
 */
public class UserAlreadyExistException extends RuntimeException {

    /**
     * Сообщение об ошибке
     * @param name имя пользователя
     * @param email почта пользователя
     */
    public UserAlreadyExistException(String name, String email) {
        super("Пользователь с таким именем " + name + " и почтой "
                + email + " уже существует");
    }

    /**
     * Сообщение об ошибке
     * @param email почта пользователя
     */
    public UserAlreadyExistException(String email) {
        super("Пользователь с такой почтой " + email
                + " уже существует");
    }
}
