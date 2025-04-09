package ru.matveyelovskikh.naujavaspring.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.matveyelovskikh.naujavaspring.exception.EventNotFoundException;
import ru.matveyelovskikh.naujavaspring.exception.UserAlreadyExistException;
import ru.matveyelovskikh.naujavaspring.exception.UserNotFoundException;
import ru.matveyelovskikh.naujavaspring.exception.response.ExceptionResponse;

/**
 * Обработка исключений в приложении
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * Перехват и обработка исключения EventNotFoundException
     * @param e EventNotFoundException
     * @return ExceptionResponse
     */
    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleEventNotFoundException(EventNotFoundException e) {
        return new ExceptionResponse(e.getMessage(), EventNotFoundException.CODE);
    }

    /**
     * Перехват и обработка исключения UserAlreadyExistException
     * @param e UserAlreadyExistException
     * @return ExceptionResponse
     */
    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleUserAlreadyExistException(UserAlreadyExistException e) {
        return new ExceptionResponse(e.getMessage(), UserAlreadyExistException.CODE);
    }

    /**
     * Перехват и обработка исключения UserNotFoundException
     * @param e UserNotFoundException
     * @return ExceptionResponse
     */
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleUserNotFoundException(UserNotFoundException e) {
        return new ExceptionResponse(e.getMessage(), UserNotFoundException.CODE);
    }

    /**
     * Перехват и обработка исключения IllegalStateException
     * @param e IllegalStateException
     * @return ExceptionResponse
     */
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleIllegalStateException(IllegalStateException e) {
        return new ExceptionResponse(e.getMessage(), 400);
    }

    /**
     * Перехват и обработка исключения Exception
     * @param e Exception
     * @return ExceptionResponse
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleException(Exception e) {
        return new ExceptionResponse(e.getMessage(), 500);
    }
}
