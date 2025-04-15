package ru.matveyelovskikh.naujavaspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.matveyelovskikh.naujavaspring.dto.EventCategoryDto;
import ru.matveyelovskikh.naujavaspring.dto.EventsDayDto;
import ru.matveyelovskikh.naujavaspring.dto.LocationDto;
import ru.matveyelovskikh.naujavaspring.repository.UserCrud;
import ru.matveyelovskikh.naujavaspring.service.EventsDayService;

import java.time.LocalDateTime;

@SpringBootTest
class NauJavaSpringApplicationTests {

    @Autowired
    private UserCrud userCrud;
    @Autowired
    private EventsDayService eventsDayService;

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
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
    }
}
