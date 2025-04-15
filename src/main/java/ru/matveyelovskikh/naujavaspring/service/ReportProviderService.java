package ru.matveyelovskikh.naujavaspring.service;

import ru.matveyelovskikh.naujavaspring.dto.ReportDataDto;

/**
 * Сервис поставщика данных отчета
 */
public interface ReportProviderService {

    /**
     * Собрать данные отчета
     * @return дто данных отчета
     */
    ReportDataDto collectReportData();
}
