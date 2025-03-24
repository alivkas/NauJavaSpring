package ru.matveyelovskikh.naujavaspring.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.matveyelovskikh.naujavaspring.configuration.ApplicationConfig;
import ru.matveyelovskikh.naujavaspring.interfaces.InputOutput;

/**
 * Сервис информации о приложении
 */
@Service
public class ApplicationInfoService {

    private final ApplicationConfig applicationConfig;
    private final InputOutput console;

    /**
     * Внедрение зависимостей applicationConfig, console
     * @param applicationConfig конфигурация приложения
     * @param console консольный I/O
     */
    @Autowired
    public ApplicationInfoService(ApplicationConfig applicationConfig,
                                  InputOutput console) {
        this.applicationConfig = applicationConfig;
        this.console = console;
    }

    /**
     * Вывести информацию о приложении
     * после запуска других бинов
     */
    @PostConstruct
    public void printAppInfoAfter() {
        console.output("Название приложения: " + applicationConfig.getAppName());
        console.output("Версия: " + applicationConfig.getAppVersion());
    }
}
