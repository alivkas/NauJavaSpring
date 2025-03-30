package ru.matveyelovskikh.naujavaspring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matveyelovskikh.naujavaspring.dto.EventsDayDto;
import ru.matveyelovskikh.naujavaspring.entity.*;
import ru.matveyelovskikh.naujavaspring.entity.enums.EventStatus;
import ru.matveyelovskikh.naujavaspring.events.EventsDayCreatedEvent;
import ru.matveyelovskikh.naujavaspring.exception.EventNotFoundException;
import ru.matveyelovskikh.naujavaspring.exception.UserNotFoundException;
import ru.matveyelovskikh.naujavaspring.mapstruct.EventMapper;
import ru.matveyelovskikh.naujavaspring.repository.EventsDayCrud;
import ru.matveyelovskikh.naujavaspring.repository.UserCrud;
import ru.matveyelovskikh.naujavaspring.service.EventCategoryService;
import ru.matveyelovskikh.naujavaspring.service.EventsDayService;
import ru.matveyelovskikh.naujavaspring.service.LocationService;

import java.util.List;

/**
 * Бизнес логика дневных событий
 */
@Service
public class EventsDayServiceImpl implements EventsDayService {

    private final EventsDayCrud eventsDayCrud;
    private final UserCrud userCrud;
    private final EventMapper eventMapper;
    private final LocationService locationService;
    private final EventCategoryService eventCategoryService;
    private final ApplicationEventPublisher eventPublisher;

    /**
     * Внедрение зависимостей eventsDayRepository
     * @param eventsDayCrud бин репозитория дневных событий
     * @param userCrud crud пользователя
     * @param eventMapper crud маппера событий
     * @param locationService сервис локации
     * @param eventCategoryService сервис категории
     * @param eventPublisher паблишер событий
     */
    @Autowired
    public EventsDayServiceImpl(EventsDayCrud eventsDayCrud,
                                UserCrud userCrud,
                                EventMapper eventMapper,
                                LocationService locationService,
                                EventCategoryService eventCategoryService,
                                ApplicationEventPublisher eventPublisher) {
        this.eventsDayCrud = eventsDayCrud;
        this.userCrud = userCrud;
        this.eventMapper = eventMapper;
        this.locationService = locationService;
        this.eventCategoryService = eventCategoryService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public EventsDayEntity getEventById(Long id) {
        return eventsDayCrud.findById(id).orElseThrow(()
                -> new EventNotFoundException(id));
    }

    @Override
    public List<EventsDayEntity> getAllEvents() {
        return (List<EventsDayEntity>) eventsDayCrud.findAll();
    }

    @Transactional
    @Override
    public void createEventDay(EventsDayDto eventsDay) {
        UserEntity user = userCrud.findById(eventsDay.userId()).orElseThrow(()
                -> new UserNotFoundException(eventsDay.userId()));
        EventCategoryEntity category = eventCategoryService
                .getOrCreateEventCategory(eventsDay.eventCategoryDto());
        LocationEntity location = locationService
                .getOrCreateCategory(eventsDay.locationDto());

        EventsDayEntity event = eventMapper.toEntity(
                eventsDay,
                user,
                category,
                location
        );

        eventsDayCrud.save(event);
        eventPublisher.publishEvent(new EventsDayCreatedEvent(event));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        eventsDayCrud.findById(id).orElseThrow(()
                -> new EventNotFoundException(id));
        eventsDayCrud.deleteById(id);
    }

    @Transactional
    @Override
    public void updateById(Long id, EventsDayDto eventsDay) {
        EventsDayEntity event = getEventById(id);

        if (!event.getEventStatus().equals(EventStatus.WAITING)) {
            throw new IllegalStateException("Изменение завершенного " +
                    "напоминание запрещено\n");
        }
        eventMapper.updateFromDto(eventsDay, event);
        eventsDayCrud.save(event);
    }

    @Transactional
    @Override
    public void acceptById(Long id) {
        EventsDayEntity event = getEventById(id);

        if (event.getEventStatus().equals(EventStatus.WAITING)) {
            throw new IllegalStateException("Событие с ID: " + id
                    + " еще не активно\n");
        }
        event.setEventStatus(EventStatus.CLOSED);
        eventsDayCrud.save(event);
    }
}
