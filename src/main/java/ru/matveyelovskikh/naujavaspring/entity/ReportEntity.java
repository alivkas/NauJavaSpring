package ru.matveyelovskikh.naujavaspring.entity;

import jakarta.persistence.*;
import ru.matveyelovskikh.naujavaspring.entity.base.BasicEntity;
import ru.matveyelovskikh.naujavaspring.entity.enums.ReportStatus;

/**
 * Сущность отчета
 */
@Entity
@Table(name = "tbl_report")
public class ReportEntity extends BasicEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReportStatus status;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    /**
     * Конструктор для инициализации
     */
    public ReportEntity() {
    }

    /**
     * Конструктор для создания сущности
     * @param status статус
     * @param content содержимое
     */
    public ReportEntity(ReportStatus status, String content) {
        this.status = status;
        this.content = content;
    }

    /**
     * Получить статус
     * @return статус
     */
    public ReportStatus getStatus() {
        return status;
    }

    /**
     * Установить статус
     * @param status статус
     */
    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    /**
     * Получить описание
     * @return описание
     */
    public String getContent() {
        return content;
    }

    /**
     * Установить описание
     * @param content описание
     */
    public void setContent(String content) {
        this.content = content;
    }
}
