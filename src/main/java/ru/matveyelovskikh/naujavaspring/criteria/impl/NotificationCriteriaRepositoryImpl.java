package ru.matveyelovskikh.naujavaspring.criteria.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.matveyelovskikh.naujavaspring.criteria.NotificationCriteriaRepository;
import ru.matveyelovskikh.naujavaspring.entity.NotificationEntity;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;

import java.util.List;

/**
 * Реализация взаимодействия с NotificationEntity при помощи Criteria API
 */
@Repository
public class NotificationCriteriaRepositoryImpl implements NotificationCriteriaRepository {

    private final EntityManager entityManager;

    /**
     * Внедрение зависимости EntityManager
     * @param entityManager управление сущностью
     */
    public NotificationCriteriaRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<NotificationEntity> findAllByUser(UserEntity user) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NotificationEntity> query = criteriaBuilder.createQuery(NotificationEntity.class);
        Root<NotificationEntity> notification = query.from(NotificationEntity.class);

        Predicate userPredicate = criteriaBuilder.equal(notification.get("user"), user);

        query.select(notification).where(userPredicate);

        return entityManager.createQuery(query).getResultList();
    }
}
