package ru.matveyelovskikh.naujavaspring;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.matveyelovskikh.naujavaspring.dto.EventCategoryDto;
import ru.matveyelovskikh.naujavaspring.dto.EventsDayDto;
import ru.matveyelovskikh.naujavaspring.dto.LocationDto;
import ru.matveyelovskikh.naujavaspring.entity.NotificationEntity;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;
import ru.matveyelovskikh.naujavaspring.repository.NotificationCrud;
import ru.matveyelovskikh.naujavaspring.repository.UserCrud;
import ru.matveyelovskikh.naujavaspring.service.EventsDayService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Тестирование методов репозитория, написанных при помощи
 * Query Lookup Strategies, а также JPQL
 */
@SpringBootTest
public class QueryMethodsTests {

    private final UserCrud userCrud;
    private final NotificationCrud notificationCrud;
    private final EventsDayService eventsDayService;

    /**
     * Внедрение зависимостей UserCrud, NotificationCrud, EventsDayService
     * UserService
     * @param userCrud crud пользователя
     * @param notificationCrud crud уведомления
     * @param eventsDayService сервис событий дня
     */
    @Autowired
    public QueryMethodsTests(UserCrud userCrud,
                             NotificationCrud notificationCrud,
                             EventsDayService eventsDayService) {
        this.userCrud = userCrud;
        this.notificationCrud = notificationCrud;
        this.eventsDayService = eventsDayService;
    }

    /**
     * Инициализация пользователя перед каждым тестом
     */
    @BeforeEach
    public void init() {
        UserEntity user = new UserEntity("test",
                "test",
                "test",
                Boolean.TRUE,
                Boolean.FALSE,
                Boolean.FALSE);
        userCrud.save(user);
    }

    /**
     * Удаление пользователя из бд после каждого теста
     */
    @AfterEach
    public void destroy() {
        userCrud.deleteAll();
    }

    /**
     * Тестировать проверку существования пользователя
     * по его username и email
     */
    @Test
    public void testExistsByUsernameAndEmail() {
        boolean isExist = userCrud.existsByUsernameAndEmail("test", "test");
        Assertions.assertTrue(isExist);
    }

    /**
     * Тестировать проверку с несуществующим пользователем
     */
    @Test
    public void testNotExistsByUsernameAndEmail() {
        boolean isExist = userCrud.existsByUsernameAndEmail("name", "email");
        Assertions.assertFalse(isExist);
    }

    /**
     * Тестировать нахождение всех уведомлений пользователя
     */
    @Transactional
    @Test
    public void testFindAllNotificationsByUser() {
        Long userId = userCrud.findAll().iterator().next().getId();

        EventCategoryDto categoryDto = new EventCategoryDto("job", "descr");
        LocationDto locationDto = new LocationDto("site",
                "www.site.com",
                Boolean.TRUE,
                Boolean.FALSE);

        EventsDayDto eventsDayDto = new EventsDayDto(LocalDateTime.now(),
                "message for user",
                userId,
                categoryDto,
                locationDto);
        eventsDayService.createEventDay(eventsDayDto);

        List<NotificationEntity> expectAsList = new ArrayList<>(userCrud.findById(userId)
                .get().getNotification());
        List<NotificationEntity> actual = notificationCrud.findAllByUser(
                userCrud.findById(userId).get());

        Assertions.assertEquals(expectAsList, actual);
    }
}
