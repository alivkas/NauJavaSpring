package ru.matveyelovskikh.naujavaspring.entity;

import jakarta.persistence.*;
import ru.matveyelovskikh.naujavaspring.entity.base.BasicEntity;

import java.util.List;

/**
 * Сущность категории события
 */
@Entity
@Table(name = "tbl_event_category")
public class EventCategoryEntity extends BasicEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "is_visible")
    private Boolean isVisible;
    @Column(name = "is_default")
    private Boolean isDefault;
    @Column(name = "is_archived")
    private Boolean isArchived;

    @OneToMany(mappedBy = "eventCategory", cascade = CascadeType.ALL)
    private List<EventsDayEntity> eventsDay;

    public EventCategoryEntity() {
    }

    /**
     * Конструктор EventCategoryEntity для инициализации полей
     * @param name имя категории
     * @param description описание категории
     * @param isVisible видна ли категория
     * @param isDefault по умолчанию ли категория
     * @param isArchived архивировано ли категория
     * @param eventsDay список событий дня категории
     */
    public EventCategoryEntity(String name,
                               String description,
                               Boolean isVisible,
                               Boolean isDefault,
                               Boolean isArchived,
                               List<EventsDayEntity> eventsDay) {
        this.name = name;
        this.description = description;
        this.isVisible = isVisible;
        this.isDefault = isDefault;
        this.isArchived = isArchived;
        this.eventsDay = eventsDay;
    }

    /**
     * Получить имя категории
     * @return имя категории
     */
    public String getName() {
        return name;
    }

    /**
     * Установить имя категории
     * @param name имя категории
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получить описание категории
     * @return описание категории
     */
    public String getDescription() {
        return description;
    }

    /**
     * Установить описание категории
     * @param description описание категории
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Получить видимость категории
     * @return видимость категории
     */
    public Boolean getVisible() {
        return isVisible;
    }

    /**
     * Установить видимость категории
     * @param visible видимость категории
     */
    public void setVisible(Boolean visible) {
        isVisible = visible;
    }

    /**
     * Получить статус категории по умолчанию
     * @return статус категории по умолчанию
     */
    public Boolean getDefault() {
        return isDefault;
    }

    /**
     * Установить статус категории по умолчанию
     * @param aDefault статус категории по умолчанию
     */
    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    /**
     * Получить статус архива категории
     * @return статус архива категории
     */
    public Boolean getArchived() {
        return isArchived;
    }

    /**
     * Установить статус архива категории
     * @param archived статус архива категории
     */
    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    /**
     * Получить список событий дня категории
     * @return список событий дня категории
     */
    public List<EventsDayEntity> getEventsDay() {
        return eventsDay;
    }

    /**
     * Установить список событий дня категории
     * @param eventsDay список событий дня категории
     */
    public void setEventsDay(List<EventsDayEntity> eventsDay) {
        this.eventsDay = eventsDay;
    }
}
