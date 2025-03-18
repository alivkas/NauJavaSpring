package ru.matveyelovskikh.naujavaspring.processing.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.matveyelovskikh.naujavaspring.exception.EventNotFoundException;
import ru.matveyelovskikh.naujavaspring.interfaces.Command;
import ru.matveyelovskikh.naujavaspring.interfaces.InputOutput;
import ru.matveyelovskikh.naujavaspring.service.EventsDayService;

/**
 * Определение команды /accept
 */
@Component
public class AcceptCommand implements Command {

    private final InputOutput console;
    private final EventsDayService eventsDayService;

    /**
     * Внедрение зависимостей InputOutput, eventsDayService
     * @param console консольный I/O
     * @param eventsDayService сервис дневных событий
     */
    @Autowired
    public AcceptCommand(InputOutput console,
                         EventsDayService eventsDayService) {
        this.console = console;
        this.eventsDayService = eventsDayService;
    }

    @Override
    public void execute() {
        try {
            accept();
        } catch (EventNotFoundException | IllegalStateException e) {
            console.output(e.getMessage());
        }
    }

    /**
     * Принять событие
     */
    private void accept() {
        console.output("Введите ID");
        Long id = Long.parseLong(console.input());
        eventsDayService.acceptById(id);
        console.output("Событие с ID: " + id + " успешно принято\n");
    }
}
