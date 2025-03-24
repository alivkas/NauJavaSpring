package ru.matveyelovskikh.naujavaspring.processing.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.matveyelovskikh.naujavaspring.entity.EventsDayEntity;
import ru.matveyelovskikh.naujavaspring.interfaces.Command;
import ru.matveyelovskikh.naujavaspring.interfaces.InputOutput;
import ru.matveyelovskikh.naujavaspring.service.EventsDayService;

import java.util.Map;

/**
 * Определение команды /all-events
 */
@Component
public class AllEventCommand implements Command {

    private final InputOutput console;
    private final EventsDayService eventsDayService;

    /**
     * Внедрение зависимостей InputOutput, eventsDayService
     * @param console консольный I/O
     * @param eventsDayService сервис дневных событий
     */
    @Autowired
    public AllEventCommand(InputOutput console,
                           EventsDayService eventsDayService) {
        this.console = console;
        this.eventsDayService = eventsDayService;
    }

    @Override
    public void execute() {
        allEvent();
    }

    /**
     * Вывод всех событий
     */
    private void allEvent() {
        if (eventsDayService.getAllEvents().isEmpty()) {
            console.output("Нет запланированных событий\n");
        }

        for (Map.Entry<Long, EventsDayEntity> entry : eventsDayService.getAllEvents()
                .entrySet()) {
            console.output(entry.getValue().toString());
        }
    }
}
