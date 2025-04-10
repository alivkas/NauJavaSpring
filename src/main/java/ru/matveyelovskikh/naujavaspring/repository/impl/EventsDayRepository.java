package ru.matveyelovskikh.naujavaspring.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.matveyelovskikh.naujavaspring.entity.EventsDayEntity;
import ru.matveyelovskikh.naujavaspring.repository.CrudRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Доступ к данным дневных событий, реализующий
 * CRUD интерфейс
 */
@Component
public class EventsDayRepository implements CrudRepository<EventsDayEntity, Long> {

    private final Map<Long, EventsDayEntity> eventsDayEntities;
    private final AtomicLong atomicInteger = new AtomicLong();

    /**
     * Внедрение зависимости eventsDayEntities
     * @param eventsDayEntities словарь сущностей дневных событий
     */
    @Autowired
    public EventsDayRepository(Map<Long, EventsDayEntity> eventsDayEntities) {
        this.eventsDayEntities = eventsDayEntities;
    }

    @Override
    public void create(EventsDayEntity entity) {
        entity.setId(atomicInteger.incrementAndGet());
        eventsDayEntities.put(entity.getId(), entity);
    }

    @Override
    public Optional<EventsDayEntity> read(Long id) {
        return Optional.ofNullable(eventsDayEntities.get(id));
    }

    @Override
    public Map<Long, EventsDayEntity> readAll() {
        return eventsDayEntities;
    }

    @Override
    public void update(Long id, EventsDayEntity entity) {
        entity.setId(atomicInteger.get());
        if (!id.equals(entity.getId())) {
            throw new IllegalArgumentException("ID не совпадают");
        }
        eventsDayEntities.put(id, entity);
    }

    @Override
    public void delete(Long id) {
        eventsDayEntities.remove(id);
    }
}
