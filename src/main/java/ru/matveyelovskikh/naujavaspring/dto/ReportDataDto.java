package ru.matveyelovskikh.naujavaspring.dto;

import java.util.List;

/**
 * Дто данных отчета
 * @param userCount количество пользователей
 * @param events список дто событий
 * @param userCountTime время, затраченное на подсчет пользователей
 * @param eventsDaysTime время, затраченное на подсчет событий
 * @param totalTime время, затраченное на подсчет генерации отчета
 */
public record ReportDataDto(Long userCount,
                            List<EventsDayDto> events,
                            Long userCountTime,
                            Long eventsDaysTime,
                            Long totalTime) {
}
