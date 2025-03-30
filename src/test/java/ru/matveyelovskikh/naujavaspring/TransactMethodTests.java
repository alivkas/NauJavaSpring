package ru.matveyelovskikh.naujavaspring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.matveyelovskikh.naujavaspring.dto.EventCategoryDto;
import ru.matveyelovskikh.naujavaspring.dto.EventsDayDto;
import ru.matveyelovskikh.naujavaspring.dto.LocationDto;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;
import ru.matveyelovskikh.naujavaspring.exception.UserNotFoundException;
import ru.matveyelovskikh.naujavaspring.repository.UserCrud;
import ru.matveyelovskikh.naujavaspring.service.EventsDayService;

import java.time.LocalDateTime;

/**
 * Тестирование транзакционного метода
 */
@SpringBootTest
public class TransactMethodTests {

    private final EventsDayService eventsDayService;
    private final UserCrud userCrud;

    /**
     * Внедрение зависимостей EventsDayService, UserCrud, EventsDayCrud
     * @param eventsDayService сервис событий дня
     * @param userCrud crud пользователя
     */
    @Autowired
    public TransactMethodTests(EventsDayService eventsDayService,
                               UserCrud userCrud) {
        this.eventsDayService = eventsDayService;
        this.userCrud = userCrud;
    }

    /**
     * Тестировать удачное создание события дня в транзакционном методе
     */
    @Test
    void createEventDaySuccessTest() {
        UserEntity user = new UserEntity("test",
                "test",
                "test",
                Boolean.TRUE,
                Boolean.FALSE,
                Boolean.FALSE);
        userCrud.save(user);

        EventCategoryDto categoryDto = new EventCategoryDto("hobby", "123");
        LocationDto locationDto = new LocationDto("Art school",
                "Lenina 1",
                Boolean.FALSE,
                Boolean.TRUE);

        EventsDayDto dto = new EventsDayDto(
                LocalDateTime.now(),
                "message",
                user.getId(),
                categoryDto,
                locationDto
        );

        eventsDayService.createEventDay(dto);
        Assertions.assertFalse(eventsDayService.getAllEvents().isEmpty());

        userCrud.deleteAll();
    }

    /**
     * Тестировать неудачное создание событие с откатом транзакции
     */
    @Test
    void createEventDayRollbackOnException() {
        EventCategoryDto categoryDto = new EventCategoryDto("hobby", "123");
        LocationDto locationDto = new LocationDto("Art school",
                "Lenina 1",
                Boolean.FALSE,
                Boolean.TRUE);

        EventsDayDto dto = new EventsDayDto(
                LocalDateTime.now(),
                "message",
                999L,
                categoryDto,
                locationDto
        );

        UserNotFoundException exception = Assertions.assertThrows(UserNotFoundException.class, () ->
            eventsDayService.createEventDay(dto));
        Assertions.assertEquals("Пользователя с id 999 не найдено",
                exception.getMessage());

        Assertions.assertTrue(eventsDayService.getAllEvents().isEmpty());
    }
}
