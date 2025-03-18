package ru.matveyelovskikh.naujavaspring.configuration;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.matveyelovskikh.naujavaspring.entity.EventsDayEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Конфигурация базы данных
 */
@Configuration
public class DataBaseConfig {

    /**
     * Бин контейнера событий дней
     * @return словарь дневных событий
     */
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public Map<Long, EventsDayEntity> eventsDayEntitiesContainer() {
        return new HashMap<>();
    }
}
