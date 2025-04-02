package ru.matveyelovskikh.naujavaspring.events;

import ru.matveyelovskikh.naujavaspring.entity.EventsDayEntity;

/**
 * Событие создания календарного события
 */
public record EventsDayCreatedEvent(EventsDayEntity eventsDay) {
}
