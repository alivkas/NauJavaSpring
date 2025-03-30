package ru.matveyelovskikh.naujavaspring.criteria.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.matveyelovskikh.naujavaspring.criteria.UserCriteriaRepository;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;

import java.util.Optional;

/**
 * Реализация взаимодействия с UserEntity при помощи Criteria API
 */
@Repository
public class UserCriteriaRepositoryImpl implements UserCriteriaRepository {

    private final EntityManager entityManager;

    /**
     * Внедрение зависимости EntityManager
     * @param entityManager управление сущностью
     */
    @Autowired
    public UserCriteriaRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean existsByUsernameAndEmail(String username, String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<UserEntity> user = query.from(UserEntity.class);

        Predicate usernamePredicate = criteriaBuilder.equal(user.get("username"), username);
        Predicate emailPredicate = criteriaBuilder.equal(user.get("email"), email);
        Predicate andPredicate = criteriaBuilder.and(usernamePredicate, emailPredicate);

        query.where(andPredicate);
        query.select(criteriaBuilder.count(user));

        return entityManager.createQuery(query).getSingleResult() > 0;
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> user = cq.from(UserEntity.class);

        cq.where(cb.equal(user.get("id"), id));

        try {
            UserEntity result = entityManager.createQuery(cq).getSingleResult();
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
