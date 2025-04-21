package ru.matveyelovskikh.naujavaspring.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер домашней страницы
 */
@Controller
public class HomeController {

    @GetMapping
    public String getHomePage() {
        return "home";
    }
}
