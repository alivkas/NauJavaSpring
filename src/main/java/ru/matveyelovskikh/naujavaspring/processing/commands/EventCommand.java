package ru.matveyelovskikh.naujavaspring.processing.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.matveyelovskikh.naujavaspring.exception.EventNotFoundException;
import ru.matveyelovskikh.naujavaspring.interfaces.Command;
import ru.matveyelovskikh.naujavaspring.interfaces.InputOutput;
import ru.matveyelovskikh.naujavaspring.service.EventsDayService;

/**
 * Определение команды /event
 */
@Component
public class EventCommand implements Command {

    private final InputOutput console;
    private final EventsDayService eventsDayService;

    /**
     * Внедрение зависимостей InputOutput, eventsDayService
     * @param console консольный I/O
     * @param eventsDayService сервис дневных событий
     */
    @Autowired
    public EventCommand(InputOutput console,
                        EventsDayService eventsDayService) {
        this.console = console;
        this.eventsDayService = eventsDayService;
    }

    @Override
    public void execute() {
        try {
            event();
        } catch (EventNotFoundException e) {
            console.output(e.getMessage());
        }
    }

    /**
     * Вывод события
     */
    private void event() {
        console.output("Введите ID события");
        console.output(eventsDayService.getEventById(Long.parseLong(console.input())).toString());
    }
}
