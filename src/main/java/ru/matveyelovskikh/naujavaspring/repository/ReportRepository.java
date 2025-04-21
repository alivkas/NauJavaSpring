package ru.matveyelovskikh.naujavaspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matveyelovskikh.naujavaspring.entity.ReportEntity;

/**
 * Репозиторий отчета
 */
public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
}
