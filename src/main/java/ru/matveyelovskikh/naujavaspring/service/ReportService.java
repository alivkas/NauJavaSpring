package ru.matveyelovskikh.naujavaspring.service;

import ru.matveyelovskikh.naujavaspring.dto.ReportDto;
import ru.matveyelovskikh.naujavaspring.entity.ReportEntity;

import java.util.Map;

/**
 * Сервис работы с отчетами
 */
public interface ReportService {

    /**
     * Получить отчет по id для внешнего использования
     * @param id id отчета
     * @return дто отчета
     */
    ReportDto getReportForApi(Long id);

    /**
     * Создать и получить id отчета
     * @return id
     */
    Long createAndGetId();
}
