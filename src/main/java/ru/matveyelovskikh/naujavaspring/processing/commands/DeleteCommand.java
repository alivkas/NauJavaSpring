package ru.matveyelovskikh.naujavaspring.processing.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.matveyelovskikh.naujavaspring.exception.EventNotFoundException;
import ru.matveyelovskikh.naujavaspring.interfaces.Command;
import ru.matveyelovskikh.naujavaspring.interfaces.InputOutput;
import ru.matveyelovskikh.naujavaspring.service.EventsDayService;

/**
 * Определение команды /delete
 */
@Component
public class DeleteCommand implements Command {

    private final InputOutput console;
    private final EventsDayService eventsDayService;

    /**
     * Внедрение зависимостей InputOutput, eventsDayService
     * @param console консольный I/O
     * @param eventsDayService сервис дневных событий
     */
    @Autowired
    public DeleteCommand(InputOutput console,
                         EventsDayService eventsDayService) {
        this.console = console;
        this.eventsDayService = eventsDayService;
    }

    @Override
    public void execute() {
        try {
            delete();
        } catch (EventNotFoundException e) {
            console.output(e.getMessage());
        }
    }

    /**
     * Удалить событие
     */
    private void delete() {
        console.output("Введите ID события");
        Long id = Long.parseLong(console.input());
        console.output("Вы точно хотите удалить событие? y/n");

        switch (console.input()) {
            case "n" -> {
                console.output("Удаление отклонено\n");
                return;
            }
            case "y" -> {
                eventsDayService.deleteById(id);
                console.output("Удаление выполнено\n");
            }
            default -> console.output("Неизвестная команда\n");
        }
    }
}
