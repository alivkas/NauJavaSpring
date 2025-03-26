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
    public Optional<UserEntity> findByUsernameOrEmail(String username, String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> user = query.from(UserEntity.class);

        Predicate usernamePredicate = criteriaBuilder.equal(user.get("username"), username);
        Predicate emailPredicate = criteriaBuilder.equal(user.get("email"), email);
        Predicate orPredicate = criteriaBuilder.or(usernamePredicate, emailPredicate);

        query.select(user).where(orPredicate);

        try {
            UserEntity result = entityManager.createQuery(query).getSingleResult();
            return Optional.ofNullable(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
