package ru.matveyelovskikh.naujavaspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matveyelovskikh.naujavaspring.entity.RoleEntity;

/**
 * Репозиторий сущности Role
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
