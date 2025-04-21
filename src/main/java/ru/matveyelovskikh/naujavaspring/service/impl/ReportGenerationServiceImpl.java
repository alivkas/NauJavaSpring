package ru.matveyelovskikh.naujavaspring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matveyelovskikh.naujavaspring.dto.ReportDataDto;
import ru.matveyelovskikh.naujavaspring.entity.ReportEntity;
import ru.matveyelovskikh.naujavaspring.entity.enums.ReportStatus;
import ru.matveyelovskikh.naujavaspring.exception.ReportNotFoundException;
import ru.matveyelovskikh.naujavaspring.repository.ReportRepository;
import ru.matveyelovskikh.naujavaspring.service.ReportGenerationService;
import ru.matveyelovskikh.naujavaspring.service.ReportProviderService;
import ru.matveyelovskikh.naujavaspring.utils.ReportHtmlBuilderUtils;

import java.util.concurrent.CompletableFuture;

/**
 * Реализация сервиса генерации отчета
 */
@Service
public class ReportGenerationServiceImpl implements ReportGenerationService {

    private final ReportProviderService reportProviderService;
    private final ReportRepository reportRepository;

    /**
     * Внедрение ReportProviderService, ReportRepository
     * @param reportProviderService сервис поставщика данных отчета
     * @param reportRepository репозиторий отчета
     */
    @Autowired
    public ReportGenerationServiceImpl(ReportProviderService reportProviderService,
                                       ReportRepository reportRepository) {
        this.reportProviderService = reportProviderService;
        this.reportRepository = reportRepository;
    }

    @Transactional
    @Override
    public CompletableFuture<Void> generateReport(Long reportId) {
        return CompletableFuture.runAsync(() -> {
            ReportEntity report = reportRepository.findById(reportId)
                    .orElseThrow(() -> new ReportNotFoundException(reportId));

            try {
                ReportDataDto reportDataDto = reportProviderService.collectReportData();
                String htmlContent = ReportHtmlBuilderUtils.buildHtml(reportDataDto);

                report.setStatus(ReportStatus.FINISHED);
                report.setContent(htmlContent);

                reportRepository.save(report);
            } catch (Exception e) {
                report.setStatus(ReportStatus.ERROR);
                report.setContent("Ошибка генерации: " + e.getMessage());

                reportRepository.save(report);
            }
        });
    }
}
