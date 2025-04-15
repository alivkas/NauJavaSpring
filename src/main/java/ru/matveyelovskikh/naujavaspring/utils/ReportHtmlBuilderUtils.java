package ru.matveyelovskikh.naujavaspring.utils;


import ru.matveyelovskikh.naujavaspring.constants.Template;
import ru.matveyelovskikh.naujavaspring.dto.EventsDayDto;
import ru.matveyelovskikh.naujavaspring.dto.ReportDataDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Утилитный класс для создания html страницы отчета
 */
public class ReportHtmlBuilderUtils {

    /**
     * Генерация html страницы отчета
     * @param data дто данных отчета
     * @return html страница в виде строки
     */
    public static String buildHtml(ReportDataDto data) {
        try {
            long userCount = data.userCount() != null
                    ? data.userCount()
                    : 0L;
            long userCountTime = data.userCountTime() != null
                    ? data.userCountTime()
                    : 0L;
            long eventsDaysTime = data.eventsDaysTime() != null
                    ? data.eventsDaysTime()
                    : 0L;
            long totalTime = data.totalTime() != null
                    ? data.totalTime()
                    : 0L;

            return String.format(
                    Template.REPORT_TEMPLATE,
                    userCount,
                    userCountTime,
                    eventsDaysTime,
                    getHtmlContent(data.events()),
                    totalTime
            );
        } catch (Exception e) {
            throw new RuntimeException("Ошибка генерации HTML: " + e.getMessage());
        }
    }

    /**
     * Получить содержимое сущности в виде html
     * @param eventsDays список дто сущности EventsDay
     * @return html формат данных сущности
     */
    private static String getHtmlContent(List<EventsDayDto> eventsDays) {
        if (eventsDays == null || eventsDays.isEmpty()) {
            return "<tr><td>Нет данных</td></tr>";
        }

        return eventsDays.stream()
                .map(e -> {
                    String date = formatDateTime(e.calendar());
                    String message = e.message() != null
                            ? e.message()
                            : "";
                    String userId = String.valueOf(e.userId() != null
                            ? e.userId()
                            : "");
                    String category = e.eventCategoryDto() != null
                            ? e.eventCategoryDto().name()
                            : "N/A";
                    String location = e.locationDto() != null
                            ? e.locationDto().address()
                            : "N/A";

                    return String.format(
                            "<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
                            date,
                            message,
                            userId,
                            category,
                            location
                    );
                })
                .collect(Collectors.joining());
    }

    /**
     * Отформатировать дату в строку
     * @param dateTime дата
     * @return дата в виде строки
     */
    private static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "N/A";
        }
        return dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }
}
