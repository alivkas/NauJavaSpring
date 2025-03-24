package ru.matveyelovskikh.naujavaspring.processing.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.matveyelovskikh.naujavaspring.dto.EventsDayDto;
import ru.matveyelovskikh.naujavaspring.interfaces.Command;
import ru.matveyelovskikh.naujavaspring.interfaces.InputOutput;
import ru.matveyelovskikh.naujavaspring.service.EventsDayService;
import ru.matveyelovskikh.naujavaspring.utils.InputProcessingUtils;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Определение команды /create
 */
@Component
public class CreateCommand implements Command {

    private final InputOutput console;
    private final EventsDayService eventsDayService;
    private final InputProcessingUtils inputProcessingUtils = new InputProcessingUtils();

    /**
     * Внедрение зависимостей InputOutput, eventsDayService
     * @param console консольный I/O
     * @param eventsDayService сервис дневных событий
     */
    @Autowired
    public CreateCommand(InputOutput console,
                         EventsDayService eventsDayService) {
        this.console = console;
        this.eventsDayService = eventsDayService;
    }

    @Override
    public void execute() {
        try {
            create();
        } catch (DateTimeException e) {
            console.output("Ошибка: неверный формат даты или времени. " +
                    "Пожалуйста, введите данные в правильном формате.\n");
        }
    }

    /**
     * Создать событие
     */
    private void create() {
        console.output("Введите дату в формате YYYY-MM-DD:");
        String textDate = console.input();
        console.output("Введите время в формате HH-MM:");
        String textTime = console.input();
        LocalDateTime calendarDate = inputProcessingUtils
                .getFormattedLocalDateTime(textDate, textTime);

        console.output("Введите напоминание:");
        String message = console.input();

        eventsDayService.createEventDay(new EventsDayDto(calendarDate, message));
        console.output("Событие успешно создано!\n");
    }
}
