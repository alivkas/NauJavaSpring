package ru.matveyelovskikh.naujavaspring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.matveyelovskikh.naujavaspring.entity.EventsDayEntity;
import ru.matveyelovskikh.naujavaspring.entity.enums.EventStatus;
import ru.matveyelovskikh.naujavaspring.interfaces.InputOutput;
import ru.matveyelovskikh.naujavaspring.service.EventsDayService;
import ru.matveyelovskikh.naujavaspring.service.NotificationService;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Реализация сервиса уведомлений
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    private final EventsDayService eventsDayService;
    private final InputOutput console;

    /**
     * Внедрение зависимостей eventsDayService, console
     * @param eventsDayService сервис событий дня
     * @param console консоль
     */
    @Autowired
    public NotificationServiceImpl(EventsDayService eventsDayService,
                                   InputOutput console) {
        this.eventsDayService = eventsDayService;
        this.console = console;
    }

    @Override
    public boolean shouldNotify(LocalDateTime date) {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(date) && now.isBefore(date.plusMinutes(1));
    }

    @Scheduled(cron = "${cron.event.time}")
    @Override
    public void eventNotify() {
        Map<Long, EventsDayEntity> events = eventsDayService.getAllEvents();
        for (Map.Entry<Long, EventsDayEntity> entry : events.entrySet()) {
            EventsDayEntity entity = entry.getValue();

            if (shouldNotify(entity.getCalendar())) {
                entity.setEventStatus(EventStatus.ACTIVE);
                console.output("================================================\n"
                        + "Напоминание от " + entity.getCalendar() + "\n"
                        + "Сообщение: " + entity.getMessage() + "\n"
                        + "================================================\n");
            }
        }
    }
}
