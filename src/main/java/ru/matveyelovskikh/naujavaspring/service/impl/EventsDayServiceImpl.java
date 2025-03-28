package ru.matveyelovskikh.naujavaspring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.matveyelovskikh.naujavaspring.dto.EventsDayDto;
import ru.matveyelovskikh.naujavaspring.entity.EventsDayEntity;
import ru.matveyelovskikh.naujavaspring.entity.enums.EventStatus;
import ru.matveyelovskikh.naujavaspring.exception.EventNotFoundException;
import ru.matveyelovskikh.naujavaspring.repository.impl.EventsDayRepository;
import ru.matveyelovskikh.naujavaspring.service.EventsDayService;

import java.util.Map;

/**
 * Бизнес логика дневных событий
 */
@Service
public class EventsDayServiceImpl implements EventsDayService {

    private final EventsDayRepository eventsDayRepository;

    /**
     * Внедрение зависимостей eventsDayRepository
     * @param eventsDayRepository бин репозитория дневных событий
     */
    @Autowired
    public EventsDayServiceImpl(EventsDayRepository eventsDayRepository) {
        this.eventsDayRepository = eventsDayRepository;
    }

    @Override
    public EventsDayEntity getEventById(Long id) {
        return eventsDayRepository.read(id).orElseThrow(()
                -> new EventNotFoundException(id));
    }

    @Override
    public Map<Long, EventsDayEntity> getAllEvents() {
        return eventsDayRepository.readAll();
    }

    @Override
    public void createEventDay(EventsDayDto eventsDay) {
        EventsDayEntity event = new EventsDayEntity(eventsDay.calendar(),
                                                    eventsDay.message(),
                                                    EventStatus.WAITING);

        eventsDayRepository.create(event);
    }

    @Override
    public void deleteById(Long id) {
        eventsDayRepository.read(id).orElseThrow(()
                -> new EventNotFoundException(id));
        eventsDayRepository.delete(id);
    }

    @Override
    public void updateById(Long id, EventsDayDto eventsDay) {
        eventsDayRepository.read(id).orElseThrow(()
                -> new EventNotFoundException(id));
        if (!getEventById(id).getEventStatus().equals(EventStatus.WAITING)) {
            throw new IllegalStateException("Изменение завершенного " +
                    "напоминание запрещено\n");
        }

        EventsDayEntity entity = new EventsDayEntity(eventsDay.calendar(),
                                                     eventsDay.message(),
                                                     EventStatus.WAITING);

        eventsDayRepository.update(id, entity);
    }

    @Override
    public void acceptById(Long id) {
        EventsDayEntity entity = getEventById(id);
        if (entity.getEventStatus().equals(EventStatus.WAITING)) {
            throw new IllegalStateException("Событие с ID: " + id + " еще не активно\n");
        }
        eventsDayRepository.read(id).orElseThrow(()
                -> new EventNotFoundException(id));
        entity.setEventStatus(EventStatus.CLOSED);
    }
}
