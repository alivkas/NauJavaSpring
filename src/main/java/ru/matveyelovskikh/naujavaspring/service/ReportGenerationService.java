package ru.matveyelovskikh.naujavaspring.service;

import java.util.concurrent.CompletableFuture;

/**
 * Сервис генерации отчета
 */
public interface ReportGenerationService {

    /**
     * Генерация отчета
     * @param reportId id отчета
     * @return асинхронное выполнение метода
     */
    CompletableFuture<Void> generateReport(Long reportId);
}
