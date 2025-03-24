package ru.matveyelovskikh.naujavaspring.entity;

import jakarta.persistence.*;
import ru.matveyelovskikh.naujavaspring.entity.base.BasicEntity;

import java.util.List;

/**
 * Сущность места проведения
 */
@Entity
@Table(name = "tbl_location")
public class LocationEntity extends BasicEntity {

    @Column(name = "place")
    private String place;
    @Column(name = "address")
    private String address;
    @Column(name = "is_online")
    private Boolean isOnline;
    @Column(name = "is_available")
    private Boolean isAvailable;
    @Column(name = "is_archived")
    private Boolean isArchived;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<EventsDayEntity> eventsDay;

    public LocationEntity() {
    }

    /**
     * Конструктор LocationEntity для инициализации полей
     * @param place название места
     * @param address адрес места
     * @param isOnline онлайн ли мероприятие
     * @param isAvailable доступно ли мероприятие
     * @param isArchived архивировано ли мероприятие
     */
    public LocationEntity(String place,
                          String address,
                          Boolean isOnline,
                          Boolean isAvailable,
                          Boolean isArchived) {
        this.place = place;
        this.address = address;
        this.isOnline = isOnline;
        this.isAvailable = isAvailable;
        this.isArchived = isArchived;
    }

    /**
     * Получить место
     * @return место
     */
    public String getPlace() {
        return place;
    }

    /**
     * Установить место
     * @param place место
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * Получить адрес
     * @return адрес
     */
    public String getAddress() {
        return address;
    }

    /**
     * Установить адрес
     * @param address адрес
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Получить статус онлайна мероприятия
     * @return статус онлайна мероприятия
     */
    public Boolean getOnline() {
        return isOnline;
    }

    /**
     * Установить статус онлайна мероприятия
     * @param online статус онлайна мероприятия
     */
    public void setOnline(Boolean online) {
        isOnline = online;
    }

    /**
     * Получить статус доступности мероприятия
     * @return статус доступности мероприятия
     */
    public Boolean getAvailable() {
        return isAvailable;
    }

    /**
     * Установить статус доступности мероприятия
     * @param available статус доступности мероприятия
     */
    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    /**
     * Получить статус архива мероприятия
     * @return статус архива мероприятия
     */
    public Boolean getArchived() {
        return isArchived;
    }

    /**
     * Установить статус архива мероприятия
     * @param archived статус архива мероприятия
     */
    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    /**
     * Получить список событий дня места
     * @return список событий дня места
     */
    public List<EventsDayEntity> getEventsDay() {
        return eventsDay;
    }

    /**
     * Установить список событий дня места
     * @param eventsDay список событий дня места
     */
    public void setEventsDay(List<EventsDayEntity> eventsDay) {
        this.eventsDay = eventsDay;
    }
}
