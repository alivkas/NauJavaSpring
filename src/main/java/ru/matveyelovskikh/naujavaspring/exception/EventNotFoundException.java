package ru.matveyelovskikh.naujavaspring.exception;

/**
 * Исключение, которое вызывается, когда событие не найдено
 */
public class EventNotFoundException extends RuntimeException {

    /**
     * Сообщение исключения
     * @param id id исключения
     */
    public EventNotFoundException(Long id) {
        super("Запись с id: " + id + " не найдена");
    }
}
