package ru.matveyelovskikh.naujavaspring.processing.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.matveyelovskikh.naujavaspring.interfaces.Command;
import ru.matveyelovskikh.naujavaspring.interfaces.InputOutput;

/**
 * Определение команды /info
 */
@Component
public class InfoCommand implements Command {

    private final InputOutput console;

    /**
     * Внедрение зависимости InputOutput
     * @param console консольный I/O
     */
    @Autowired
    public InfoCommand(InputOutput console) {
        this.console = console;
    }

    @Override
    public void execute() {
        info();
    }

    /**
     * Вывод списка доступных команд
     */
    private void info() {
        console.output("""
                /info - информация о командах
                /create - создать напоминание
                /all-events - посмотреть все напоминания
                /event - посмотреть определенное напоминание
                /delete - удалить событие
                /update - изменить событие
                /accept - принять активное событие
                """);
    }
}
