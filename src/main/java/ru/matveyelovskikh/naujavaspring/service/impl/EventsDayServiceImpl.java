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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Бизнес логика дневных событий
 */
@Service
public class EventsDayServiceImpl implements EventsDayService {

    private final EventsDayRepository eventsDayRepository;
    private final AtomicInteger atomicInteger = new AtomicInteger();

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
        if (eventsDayRepository.read(id).isEmpty()) {
            throw new EventNotFoundException(id);
        }
        return eventsDayRepository.read(id).get();
    }

    @Override
    public Map<Long, EventsDayEntity> getAllEvents() {
        return eventsDayRepository.readAll();
    }

    @Override
    public void createEventDay(EventsDayDto eventsDay) {
        EventsDayEntity event =
                new EventsDayEntity((long) atomicInteger.incrementAndGet(),
                eventsDay.calendar(),
                eventsDay.message(),
                EventStatus.WAITING);

        eventsDayRepository.create(event);
    }

    @Override
    public void deleteById(Long id) {
        if (eventsDayRepository.read(id).isEmpty()) {
            throw new EventNotFoundException(id);
        }
        eventsDayRepository.delete(id);
    }

    @Override
    public void updateById(Long id, EventsDayDto eventsDay) {
        if (eventsDayRepository.read(id).isEmpty()) {
            throw new EventNotFoundException(id);
        }
        if (!getEventById(id).getEventStatus().equals(EventStatus.WAITING)) {
            throw new IllegalStateException("Изменение завершенного " +
                    "напоминание запрещено\n");
        }

        EventsDayEntity entity =
                new EventsDayEntity((long) atomicInteger.get(),
                eventsDay.calendar(),
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
        if (eventsDayRepository.read(id).isEmpty()) {
            throw new EventNotFoundException(id);
        }
        entity.setEventStatus(EventStatus.CLOSED);
    }
}
