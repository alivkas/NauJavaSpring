package ru.matveyelovskikh.naujavaspring.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.matveyelovskikh.naujavaspring.dto.UserDto;
import ru.matveyelovskikh.naujavaspring.service.UserService;

/**
 * Web контроллер регистрации пользователя
 */
@Controller
public class RegistrationController {

    private final UserService userService;

    /**
     * Внедрение UserService
     * @param userService сервис пользователя
     */
    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registration() {
        return "register";
    }


    @PostMapping("/register")
    public String registerUser(UserDto user, Model model) {
        try {
            userService.createUser(user);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("message", "User exists");
            return "register";
        }
    }
}
