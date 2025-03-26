package ru.matveyelovskikh.naujavaspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.matveyelovskikh.naujavaspring.dto.EventCategoryDto;
import ru.matveyelovskikh.naujavaspring.dto.EventsDayDto;
import ru.matveyelovskikh.naujavaspring.dto.LocationDto;
import ru.matveyelovskikh.naujavaspring.dto.UserDto;
import ru.matveyelovskikh.naujavaspring.service.EventsDayService;
import ru.matveyelovskikh.naujavaspring.service.UserService;

import java.time.LocalDateTime;

@SpringBootTest
class NauJavaSpringApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private EventsDayService eventsDayService;

    @Test
    void contextLoads() {
    }

    @Test
    public void userCreateTest() {
        UserDto userDto = new UserDto("name2", "123", "fmail");
        userService.createUser(userDto);
    }

    @Test
    public void createEventTest() {
        EventCategoryDto categoryDto = new EventCategoryDto("spring32", "999");
        LocationDto locationDto = new LocationDto("rrreee",
                "3322222",
                Boolean.TRUE,
                Boolean.FALSE);

        EventsDayDto eventsDayDto = new EventsDayDto(LocalDateTime.now(),
                "12313131231",
                1L,
                categoryDto,
                locationDto);
        eventsDayService.createEventDay(eventsDayDto);
    }
}
