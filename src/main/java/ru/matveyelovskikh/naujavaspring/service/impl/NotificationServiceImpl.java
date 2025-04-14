package ru.matveyelovskikh.naujavaspring.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.matveyelovskikh.naujavaspring.dto.NotificationDto;
import ru.matveyelovskikh.naujavaspring.entity.EventsDayEntity;
import ru.matveyelovskikh.naujavaspring.entity.NotificationEntity;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;
import ru.matveyelovskikh.naujavaspring.entity.enums.EventStatus;
import ru.matveyelovskikh.naujavaspring.exception.EventNotFoundException;
import ru.matveyelovskikh.naujavaspring.exception.UserNotFoundException;
import ru.matveyelovskikh.naujavaspring.mapstruct.NotificationMapper;
import ru.matveyelovskikh.naujavaspring.repository.EventsDayCrud;
import ru.matveyelovskikh.naujavaspring.repository.NotificationCrud;
import ru.matveyelovskikh.naujavaspring.repository.UserCrud;
import ru.matveyelovskikh.naujavaspring.service.EventsDayService;
import ru.matveyelovskikh.naujavaspring.service.NotificationService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Реализация сервиса уведомлений
 */
@Service

public class NotificationServiceImpl implements NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final EventsDayService eventsDayService;
    private final NotificationMapper notificationMapper;
    private final UserCrud userCrud;
    private final EventsDayCrud eventsDayCrud;
    private final NotificationCrud notificationCrud;

    /**
     * Внедрение зависимостей eventsDayService, notificationMapper,
     * userCrud, eventsDayCrud, notificationCrud
     * @param eventsDayService сервис событий дня
     * @param notificationMapper маппер уведомлений
     * @param userCrud crud пользователя
     * @param eventsDayCrud crud событий дня
     * @param notificationCrud crud уведомлений
     */
    @Autowired
    public NotificationServiceImpl(EventsDayService eventsDayService,
                                   NotificationMapper notificationMapper,
                                   UserCrud userCrud,
                                   EventsDayCrud eventsDayCrud,
                                   NotificationCrud notificationCrud) {
        this.eventsDayService = eventsDayService;
        this.notificationMapper = notificationMapper;
        this.userCrud = userCrud;
        this.eventsDayCrud = eventsDayCrud;
        this.notificationCrud = notificationCrud;
    }

    @Override
    public boolean shouldNotify(LocalDateTime date) {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(date) && now.isBefore(date.plusMinutes(1));
    }

    @Scheduled(cron = "${cron.event.time}")
    @Override
    public void eventNotify() {
        List<EventsDayEntity> events = eventsDayService.getAllEvents();
        for (EventsDayEntity event : events) {
            if (shouldNotify(event.getCalendar())) {
                event.setEventStatus(EventStatus.ACTIVE);
                log.info("""
                        ================================================
                        Напоминание от {}
                        Сообщение: {}
                        ================================================
                        """,
                        event.getCalendar(),
                        event.getMessage());
            }
        }
    }

    @Override
    public NotificationEntity createAndGetNotify(NotificationDto notificationDto) {
        UserEntity user = userCrud.findById(notificationDto.userId()).orElseThrow(()
                -> new UserNotFoundException(notificationDto.userId()));
        EventsDayEntity eventsDay = eventsDayCrud.findById(notificationDto.eventsDayId()).orElseThrow(()
                -> new EventNotFoundException(notificationDto.eventsDayId()));

        return notificationMapper.toEntity(notificationDto,
                user,
                eventsDay);
    }

    @Override
    public List<NotificationDto> getAllNotificationsByUser(Long userId) {
        UserEntity user = userCrud.findById(userId).orElseThrow(()
                -> new UserNotFoundException(userId));
        List<NotificationEntity> notifications = notificationCrud.findAllByUser(user);
        return notificationMapper.toDtoList(notifications);
    }
}
