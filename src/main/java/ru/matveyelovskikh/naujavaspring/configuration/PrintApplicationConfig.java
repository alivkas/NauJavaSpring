package ru.matveyelovskikh.naujavaspring.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import ru.matveyelovskikh.naujavaspring.interfaces.InputOutput;
import ru.matveyelovskikh.naujavaspring.processing.CommandProcessing;

/**
 * Конфигурация вывода данных приложения
 */
@Configuration
public class PrintApplicationConfig {

    private final InputOutput console;
    private final CommandProcessing commandProcessing;

    /**
     * Внедрение зависимостей console, commandProcessing
     * @param console консольный I/O
     * @param commandProcessing обработка команд
     */
    @Autowired
    public PrintApplicationConfig(InputOutput console,
                                  CommandProcessing commandProcessing) {
        this.commandProcessing = commandProcessing;
        this.console = console;
    }

    /**
     * Бин для работы с командной строкой
     * @return аргументы командной строки
     */
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public CommandLineRunner commandLineRunner() {
        return args -> {
            console.output("""
                    Введите команду /info для информации о других командах
                    Введите команду /exit для выхода из приложения
                    """);
            while (true) {
                String input = console.input();
                if ("/exit".equalsIgnoreCase(input.trim())) {
                    console.output("Выход из программы...");
                    break;
                }
                commandProcessing.handleCommand(input);
            }
        };
    }
}
