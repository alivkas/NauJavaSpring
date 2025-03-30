package ru.matveyelovskikh.naujavaspring;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.matveyelovskikh.naujavaspring.criteria.NotificationCriteriaRepository;
import ru.matveyelovskikh.naujavaspring.criteria.UserCriteriaRepository;
import ru.matveyelovskikh.naujavaspring.dto.EventCategoryDto;
import ru.matveyelovskikh.naujavaspring.dto.EventsDayDto;
import ru.matveyelovskikh.naujavaspring.dto.LocationDto;
import ru.matveyelovskikh.naujavaspring.entity.NotificationEntity;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;
import ru.matveyelovskikh.naujavaspring.repository.UserCrud;
import ru.matveyelovskikh.naujavaspring.service.EventsDayService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Тестирование методов, написанных при помощи Criteria API
 */
@SpringBootTest
public class CriteriaMethodsTests {

    private final UserCriteriaRepository userCriteriaRepository;
    private final NotificationCriteriaRepository notificationCriteriaRepository;
    private final EventsDayService eventsDayService;
    private final UserCrud userCrud;

    /**
     * Внедрение зависимостей UserCriteriaRepository, NotificationCriteriaRepository,
     * EventsDayService, UserService
     * @param userCriteriaRepository репозиторий criteria пользователя
     * @param notificationCriteriaRepository репозиторий criteria уведомления
     * @param eventsDayService сервис событий дня
     * @param userCrud crud пользователя
     */
    @Autowired
    public CriteriaMethodsTests(UserCriteriaRepository userCriteriaRepository,
                                NotificationCriteriaRepository notificationCriteriaRepository,
                                EventsDayService eventsDayService,
                                UserCrud userCrud) {
        this.userCriteriaRepository = userCriteriaRepository;
        this.notificationCriteriaRepository = notificationCriteriaRepository;
        this.eventsDayService = eventsDayService;
        this.userCrud = userCrud;
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
        boolean isExist = userCriteriaRepository.existsByUsernameAndEmail("test",
                "test");
        Assertions.assertTrue(isExist);
    }

    /**
     * Тестировать проверку с несуществующим пользователем
     */
    @Test
    public void testNotExistsByUsernameAndEmail() {
        boolean isExist = userCriteriaRepository.existsByUsernameAndEmail("name", "email");
        Assertions.assertFalse(isExist);
    }

    /**
     * Тестировать нахождение всех уведомлений пользователя
     */
    @Transactional
    @Test
    public void testFindAllNotificationsByUser() {
        Long userId = userCrud.findAll().iterator().next().getId();

        EventCategoryDto categoryDto = new EventCategoryDto("school", "descr");
        LocationDto locationDto = new LocationDto("school 1",
                "Lenina 1",
                Boolean.FALSE,
                Boolean.FALSE);

        EventsDayDto eventsDayDto = new EventsDayDto(LocalDateTime.now(),
                "message for student",
                userId,
                categoryDto,
                locationDto);
        eventsDayService.createEventDay(eventsDayDto);

        List<NotificationEntity> expectAsList = new ArrayList<>(userCriteriaRepository.findById(userId)
                .get().getNotification());
        List<NotificationEntity> actual = notificationCriteriaRepository.findAllByUser(userCrud
                .findById(userId).get());

        Assertions.assertEquals(expectAsList, actual);
    }
}
