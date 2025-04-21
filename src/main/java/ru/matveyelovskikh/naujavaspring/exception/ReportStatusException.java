package ru.matveyelovskikh.naujavaspring.exception;

import ru.matveyelovskikh.naujavaspring.entity.enums.ReportStatus;

/**
 * Исключение, которое возникает при каком-либо статусе
 */
public class ReportStatusException extends RuntimeException {

    public final static Integer CODE = 400;

    /**
     * Сообщение исключения
     * @param reportStatus статус
     */
    public ReportStatusException(ReportStatus reportStatus) {
        super("Отчет в статусе " + reportStatus.name());
    }
}
