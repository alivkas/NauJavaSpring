package ru.matveyelovskikh.naujavaspring.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.matveyelovskikh.naujavaspring.dto.ReportDto;
import ru.matveyelovskikh.naujavaspring.service.ReportGenerationService;
import ru.matveyelovskikh.naujavaspring.service.ReportService;

import java.util.HashMap;
import java.util.Map;

/**
 * REST контроллер отчета
 */
@RestController
@RequestMapping("/api/public/reports")
public class ReportController {

    private final ReportService reportService;
    private final ReportGenerationService reportGenerationService;

    /**
     * Внедрение ReportService, ReportGenerationService
     * @param reportService сервис отчета
     * @param reportGenerationService сервис генерации отчета
     */
    @Autowired
    public ReportController(ReportService reportService,
                            ReportGenerationService reportGenerationService) {
        this.reportService = reportService;
        this.reportGenerationService = reportGenerationService;
    }

    /**
     * POST запрос на создание отчета и его асинхронную генерацию
     * @return ответ в виде мапы с id отчета
     */
    @PostMapping
    public ResponseEntity<Map<String, Long>> createReport() {
        Long reportId = reportService.createAndGetId();
        reportGenerationService.generateReport(reportId);

        Map<String, Long> responseMap = new HashMap<>();
        responseMap.put("ID отчета", reportId);

        return ResponseEntity.ok().body(responseMap);
    }

    /**
     * Получить отчет по id
     * @param reportId id отчета
     * @return ответ в виде дто отчета
     */
    @GetMapping("/{reportId}")
    public ResponseEntity<ReportDto> getReport(@PathVariable Long reportId) {
        ReportDto reportDto = reportService.getReportForApi(reportId);

        return ResponseEntity.ok().body(reportDto);
    }
}
