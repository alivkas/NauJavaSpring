package ru.matveyelovskikh.naujavaspring.configuration;

import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация приложения
 */
@Configuration
public class ApplicationConfig {

    private final String appName = "NotifyCalendar";
    private final String appVersion = "v1.0.1";

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
