package ru.matveyelovskikh.naujavaspring.exception.response;

/**
 * Ответ ошибки
 * @param message сообщение ошибки
 * @param code код ошибки
 */
public record ExceptionResponse(String message,
                                Integer code) {
}
