package ru.matveyelovskikh.naujavaspring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурация Spring Security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Бин кодирования пароля
     * @return реализация кодировщика BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Бин фильтрации доступа различных пользователей
     * @param http билдер доступа
     * @return сконфигурированный доступ
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request -> request
                .requestMatchers("/api/public/**," +
                        "/register").permitAll()
                .requestMatchers("/swagger-ui/**," +
                        "/v3/api-docs/**," +
                        "/api/private/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
