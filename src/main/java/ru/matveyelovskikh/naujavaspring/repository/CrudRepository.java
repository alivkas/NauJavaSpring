package ru.matveyelovskikh.naujavaspring.repository;

import java.util.Map;
import java.util.Optional;

/**
 * Интерфейс CRUD операций
 */
public interface CrudRepository<T, ID> {

    /**
     * Создание сущности
     * @param entity сущность
     */
    void create(T entity);

    /**
     * Прочитать сущность по id
     * @param id id сущности
     * @return сущность
     */
    Optional<T> read(ID id);

    /**
     * Прочитать все сущности
     * @return список сущностей
     */
    Map<ID, T> readAll();

    /**
     * Обновить сущность по id
     * @param id id сущности
     * @param entity сущность
     */
    void update(ID id, T entity);

    /**
     * Удалить сущность по id
     * @param id id сущности
     */
    void delete(ID id);
}
