package ru.matveyelovskikh.naujavaspring.processing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.matveyelovskikh.naujavaspring.configuration.CommandsConfig;
import ru.matveyelovskikh.naujavaspring.interfaces.Command;
import ru.matveyelovskikh.naujavaspring.interfaces.InputOutput;

/**
 * Обработка команд
 */
@Component
public class CommandProcessing {

    private final InputOutput console;
    private final CommandsConfig commandsConfig;

    /**
     * Внедрение зависимостей console, commandsConfig
     * @param console консольный I/O
     * @param commandsConfig конфигурация команд
     */
    @Autowired
    public CommandProcessing(InputOutput console,
                             CommandsConfig commandsConfig) {
        this.console = console;
        this.commandsConfig = commandsConfig;
    }

    /**
     * Перехватить команду
     * @param input ввод команды
     */
    public void handleCommand(String input) {
        Command command = commandsConfig.getCommandByCommand(input);
        try {
            command.execute();
        } catch (NumberFormatException e) {
            console.output("Неверный формат ввода данных\n");
        } catch (NullPointerException e) {
            console.output("Неизвестная команда\n");
        }
    }
}
