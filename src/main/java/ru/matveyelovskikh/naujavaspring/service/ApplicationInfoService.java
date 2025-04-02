package ru.matveyelovskikh.naujavaspring.service;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.matveyelovskikh.naujavaspring.configuration.ApplicationConfig;

/**
 * Сервис информации о приложении
 */
@Service
public class ApplicationInfoService {

    private static final Logger log = LoggerFactory.getLogger(ApplicationInfoService.class);

    private final ApplicationConfig applicationConfig;

    /**
     * Внедрение зависимостей applicationConfig, console
     * @param applicationConfig конфигурация приложения
     */
    @Autowired
    public ApplicationInfoService(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    /**
     * Вывести информацию о приложении
     * после запуска других бинов
     */
    @PostConstruct
    public void printAppInfoAfter() {
        log.info("Название приложения: {}", applicationConfig.getAppName());
        log.info("Версия: {}", applicationConfig.getAppVersion());
    }
}
