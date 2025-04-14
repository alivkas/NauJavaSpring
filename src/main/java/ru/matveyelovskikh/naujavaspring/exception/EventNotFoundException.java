package ru.matveyelovskikh.naujavaspring.exception;

/**
 * Исключение, которое вызывается, когда событие не найдено
 */
public class EventNotFoundException extends RuntimeException {

    public final static Integer CODE = 404;

    /**
     * Сообщение исключения
     * @param id id события
     */
    public EventNotFoundException(Long id) {
        super("Запись с id: " + id + " не найдена");
    }
}
