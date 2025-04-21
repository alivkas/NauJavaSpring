package ru.matveyelovskikh.naujavaspring.dto;

import ru.matveyelovskikh.naujavaspring.entity.enums.ReportStatus;

/**
 * Дто отчета
 * @param status статус
 * @param content описание
 */
public record ReportDto(ReportStatus status,
                        String content) {
}
