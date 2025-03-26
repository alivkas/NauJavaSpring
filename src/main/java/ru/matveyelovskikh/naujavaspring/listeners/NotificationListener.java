package ru.matveyelovskikh.naujavaspring.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.matveyelovskikh.naujavaspring.dto.NotificationDto;
import ru.matveyelovskikh.naujavaspring.entity.EventsDayEntity;
import ru.matveyelovskikh.naujavaspring.entity.NotificationEntity;
import ru.matveyelovskikh.naujavaspring.events.EventsDayCreatedEvent;
import ru.matveyelovskikh.naujavaspring.repository.EventsDayCrud;
import ru.matveyelovskikh.naujavaspring.service.NotificationService;

/**
 * Слушатель события уведомления
 */
@Service
public class NotificationListener {

    private final NotificationService notificationService;
    private final EventsDayCrud eventsDayCrud;

    /**
     * Внедрение зависимостей NotificationService, EventsDayCrud
     * @param notificationService сервис уведомлений
     * @param eventsDayCrud crud событий дня
     */
    @Autowired
    public NotificationListener(NotificationService notificationService,
                                EventsDayCrud eventsDayCrud) {
        this.notificationService = notificationService;
        this.eventsDayCrud = eventsDayCrud;
    }

    /**
     * Перехватить создание календарного события
     * @param event событие создания календарного события
     */
    @EventListener
    @Transactional(propagation = Propagation.REQUIRED)
    public void handleEventsDayCreatedEvent(EventsDayCreatedEvent event) {
        EventsDayEntity eventsDay = event.eventsDay();
        NotificationEntity notification = notificationService
                .createAndGetNotify(new NotificationDto(eventsDay.getMessage(),
                        eventsDay.getCalendar(),
                        eventsDay.getId(),
                        eventsDay.getUser().getId()));
        eventsDay.getNotification().add(notification);
        eventsDayCrud.save(eventsDay);
    }
}
