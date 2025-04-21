package ru.matveyelovskikh.naujavaspring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.matveyelovskikh.naujavaspring.dto.EventsDayDto;
import ru.matveyelovskikh.naujavaspring.dto.ReportDataDto;
import ru.matveyelovskikh.naujavaspring.service.EventsDayService;
import ru.matveyelovskikh.naujavaspring.service.ReportProviderService;
import ru.matveyelovskikh.naujavaspring.service.UserService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Реализация сервиса поставщика данных отчета
 */
@Service
public class ReportProviderServiceImpl implements ReportProviderService {

    private final UserService userService;
    private final EventsDayService eventsDayService;

    /**
     * Внедрнение UserService, EventsDayService
     * @param userService сервис пользователя
     * @param eventsDayService сервис событий
     */
    @Autowired
    public ReportProviderServiceImpl(UserService userService,
                                     EventsDayService eventsDayService) {
        this.userService = userService;
        this.eventsDayService = eventsDayService;
    }

    @Override
    public ReportDataDto collectReportData() {
        long startTime = System.currentTimeMillis();

        CompletableFuture<Long> userCountFuture = CompletableFuture.supplyAsync(() -> {
            Thread.currentThread().setName("UserCountThread");
            return (long) userService.getAllUsers().size();
        });

        CompletableFuture<List<EventsDayDto>> entitiesFuture
                = CompletableFuture.supplyAsync(() -> {
            Thread.currentThread().setName("EventsDayListThread");
            return eventsDayService.getAllEventsApi();
        });

        CompletableFuture.allOf(userCountFuture, entitiesFuture).join();

        long userCount = userCountFuture.join();
        List<EventsDayDto> events = entitiesFuture.join();

        long userCountTime = System.currentTimeMillis() - startTime;
        long eventsDaysTime = System.currentTimeMillis() - startTime - userCountTime;
        long totalTime = System.currentTimeMillis() - startTime;

        return new ReportDataDto(
                userCount,
                events,
                userCountTime,
                eventsDaysTime,
                totalTime
        );
    }
}
