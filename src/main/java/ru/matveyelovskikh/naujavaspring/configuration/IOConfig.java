package ru.matveyelovskikh.naujavaspring.configuration;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.matveyelovskikh.naujavaspring.interfaces.InputOutput;
import ru.matveyelovskikh.naujavaspring.platforms.Console;

/**
 * Конфигурация ввода и вывода данных
 * в разных средах
 */
@Configuration
public class IOConfig {

    /**
     * Бин консольного ввода и вывода
     * @return консоль
     */
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public InputOutput consoleIO() {
        return new Console();
    }
}
