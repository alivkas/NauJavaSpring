package ru.matveyelovskikh.naujavaspring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.matveyelovskikh.naujavaspring.dto.UserDto;
import ru.matveyelovskikh.naujavaspring.repository.NotificationCrud;
import ru.matveyelovskikh.naujavaspring.repository.UserCrud;
import ru.matveyelovskikh.naujavaspring.service.UserService;

/**
 * Тестирование методов репозитория, написанных при помощи
 * Query Lookup Strategies, а также JPQL
 */
@SpringBootTest
public class QueryMethodsTests {

    private final UserCrud userCrud;
    private final NotificationCrud notificationCrud;
    private final UserService userService;

    /**
     * Внедрение зависимостей UserCrud, NotificationCrud,
     * UserService
     * @param userCrud crud пользователя
     * @param notificationCrud crud уведомления
     */
    @Autowired
    public QueryMethodsTests(UserCrud userCrud,
                             NotificationCrud notificationCrud,
                             UserService userService) {
        this.userCrud = userCrud;
        this.notificationCrud = notificationCrud;
        this.userService = userService;
    }

    /**
     * Тестировать проверку существования пользователя
     * по его username и email
     */
    @Test
    public void testExistsByUsernameAndEmail() {
        UserDto user1 = new UserDto("name1", "123", "email1");
        UserDto user2 = new UserDto("name2", "123", "email2");

        userService.createUser(user1);
        userService.createUser(user2);

        boolean isExist = userCrud.existsByUsernameAndEmail("name1", "email1");
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
}
