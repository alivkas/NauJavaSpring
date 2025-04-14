package ru.matveyelovskikh.naujavaspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.matveyelovskikh.naujavaspring.dto.UserDto;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;
import ru.matveyelovskikh.naujavaspring.repository.UserCrud;
import ru.matveyelovskikh.naujavaspring.service.UserService;

@SpringBootTest
class NauJavaSpringApplicationTests {

    @Autowired
    private UserCrud userCrud;
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
        UserDto user = new UserDto("test",
                "123",
                "fafasaf");
        userService.createUser(user);
    }
}
