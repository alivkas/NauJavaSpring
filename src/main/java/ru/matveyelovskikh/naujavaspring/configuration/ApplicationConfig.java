package ru.matveyelovskikh.naujavaspring.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация приложения
 */
@Configuration
public class ApplicationConfig {

    @Value("${app.name}")
    private String appName;
    @Value("${app.version}")
    private String appVersion;

    /**
     * Получить название приложения
     * @return название приложения
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Получить версию приложения
     * @return версия приложения
     */
    public String getAppVersion() {
        return appVersion;
    }
}
