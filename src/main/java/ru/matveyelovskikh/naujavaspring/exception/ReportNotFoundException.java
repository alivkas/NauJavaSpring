package ru.matveyelovskikh.naujavaspring.exception;

/**
 * Исключение, которое возникает, когда отчет не найден
 */
public class ReportNotFoundException extends RuntimeException {

    public final static Integer CODE = 404;

    /**
     * Сообщение исключения
     * @param id id пользователя
     */
    public ReportNotFoundException(Long id) {
        super("Отчета с id " + id + " не найдено");
    }
}
