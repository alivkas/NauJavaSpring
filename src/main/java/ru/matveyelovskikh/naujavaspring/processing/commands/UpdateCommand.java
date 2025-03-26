package ru.matveyelovskikh.naujavaspring.processing.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.matveyelovskikh.naujavaspring.dto.EventCategoryDto;
import ru.matveyelovskikh.naujavaspring.dto.EventsDayDto;
import ru.matveyelovskikh.naujavaspring.dto.LocationDto;
import ru.matveyelovskikh.naujavaspring.exception.EventNotFoundException;
import ru.matveyelovskikh.naujavaspring.interfaces.Command;
import ru.matveyelovskikh.naujavaspring.interfaces.InputOutput;
import ru.matveyelovskikh.naujavaspring.service.EventsDayService;
import ru.matveyelovskikh.naujavaspring.utils.InputProcessingUtils;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Определение команды /update
 */
@Component
public class UpdateCommand implements Command {

    private final InputOutput console;
    private final EventsDayService eventsDayService;
    private final InputProcessingUtils inputProcessingUtils = new InputProcessingUtils();

    /**
     * Внедрение зависимостей InputOutput, eventsDayService
     * @param console консольный I/O
     * @param eventsDayService сервис дневных событий
     */
    @Autowired
    public UpdateCommand(InputOutput console,
                         EventsDayService eventsDayService) {
        this.console = console;
        this.eventsDayService = eventsDayService;
    }

    @Override
    public void execute() {
        try {
            update();
        } catch (EventNotFoundException | IllegalStateException e) {
            console.output(e.getMessage());
        } catch (DateTimeException e) {
            console.output("Ошибка: неверный формат даты или времени. " +
                    "Пожалуйста, введите данные в правильном формате.\n");
        }
    }

    /**
     * Изменить событие
     */
    private void update() {
        console.output("Введите ID события");
        Long id = Long.parseLong(console.input());

        console.output("Введите дату в формате YYYY-MM-DD:");
        String textDate = console.input();
        console.output("Введите время в формате HH-MM:");
        String textTime = console.input();
        LocalDateTime calendarDate = inputProcessingUtils
                .getFormattedLocalDateTime(textDate, textTime);

        console.output("Введите напоминание");
        String message = console.input();

        console.output("Введите id пользователя, категории события и локации");
        String[] ids = console.input().split(" ");

        console.output("Введите название категории для события");
        String categoryName = console.input();
        console.output("Введите описание категории для события");
        String categoryDescription = console.input();
        EventCategoryDto categoryDto = new EventCategoryDto(categoryName, categoryDescription);

        console.output("Введите название локации");
        String place = console.input();
        console.output("Введите адрес локации, либо ссылку на ресурс");
        String address = console.input();
        LocationDto locationDto = new LocationDto(place, address, true, true);

        eventsDayService.updateById(id, new EventsDayDto(calendarDate,
                message,
                Long.parseLong(ids[0]),
                categoryDto,
                locationDto));

        console.output("Изменение выполнено\n");
    }
}
