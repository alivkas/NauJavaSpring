package ru.matveyelovskikh.naujavaspring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matveyelovskikh.naujavaspring.dto.ReportDto;
import ru.matveyelovskikh.naujavaspring.entity.ReportEntity;
import ru.matveyelovskikh.naujavaspring.entity.enums.ReportStatus;
import ru.matveyelovskikh.naujavaspring.exception.ReportNotFoundException;
import ru.matveyelovskikh.naujavaspring.exception.ReportStatusException;
import ru.matveyelovskikh.naujavaspring.mapstruct.ReportMapper;
import ru.matveyelovskikh.naujavaspring.repository.ReportRepository;
import ru.matveyelovskikh.naujavaspring.service.ReportService;

/**
 * Реализация сервиса отчетов
 */
@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    /**
     * Внедрение ReportRepository, ReportMapper, ReportGenerationService
     * @param reportRepository репозиторий отчетов
     * @param reportMapper маппер отчета
     */
    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository,
                             ReportMapper reportMapper) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
    }

    @Override
    public ReportDto getReportForApi(Long id) {
        ReportEntity report = reportRepository.findById(id)
                .map(r -> {
                    if (r.getStatus().equals(ReportStatus.CREATED)) {
                        throw new ReportStatusException(ReportStatus.CREATED);
                    } else if (r.getStatus().equals(ReportStatus.ERROR)) {
                        throw new ReportStatusException(ReportStatus.ERROR);
                    }
                    return r;
                })
                .orElseThrow(() -> new ReportNotFoundException(id));

        return reportMapper.toDto(report);
    }

    @Transactional
    @Override
    public Long createAndGetId() {
        ReportEntity report = new ReportEntity();
        report.setStatus(ReportStatus.CREATED);
        reportRepository.save(report);

        return report.getId();
    }
}
