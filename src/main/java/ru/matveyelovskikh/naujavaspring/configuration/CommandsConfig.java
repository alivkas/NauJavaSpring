package ru.matveyelovskikh.naujavaspring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.matveyelovskikh.naujavaspring.interfaces.Command;
import ru.matveyelovskikh.naujavaspring.interfaces.InputOutput;
import ru.matveyelovskikh.naujavaspring.processing.commands.*;
import ru.matveyelovskikh.naujavaspring.processing.common.CommandConst;
import ru.matveyelovskikh.naujavaspring.service.EventsDayService;

import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурация команд
 */
@Configuration
public class CommandsConfig {

    private final InputOutput console;
    private final EventsDayService eventsDayService;
    private final Map<String, Command> commands = new HashMap<>();

    /**
     * Внедрение зависимостей InputOutput, EventsDayService
     * @param console консольный I/O
     * @param eventsDayService сервис дневных событий
     */
    @Autowired
    public CommandsConfig(InputOutput console,
                          EventsDayService eventsDayService) {
        this.console = console;
        this.eventsDayService = eventsDayService;
    }

    /**
     * Бин инициализации всех команд
     */
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public Map<String, Command> initCommand() {
        commands.put(CommandConst.INFO, new InfoCommand(console));
        commands.put(CommandConst.CREATE, new CreateCommand(console, eventsDayService));
        commands.put(CommandConst.ALL_EVENTS, new AllEventCommand(console, eventsDayService));
        commands.put(CommandConst.EVENT, new EventCommand(console, eventsDayService));
        commands.put(CommandConst.DELETE, new DeleteCommand(console, eventsDayService));
        commands.put(CommandConst.UPDATE, new UpdateCommand(console, eventsDayService));
        commands.put(CommandConst.ACCEPT, new AcceptCommand(console, eventsDayService));

        return commands;
    }

    /**
     * Получить команду из хранилища при помощи принимаемой извне команды
     * @param command команда извне
     * @return выполнение команды
     */
    public Command getCommandByCommand(String command) {
        return commands.get(command);
    }
}
