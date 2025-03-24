package ru.matveyelovskikh.naujavaspring.entity.base;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Базовая сущность для всех сущностей
 */
@MappedSuperclass
public class BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Получить id
     * @return id
     */
    public Long getId() {
        return id;
    }
}