package ru.matveyelovskikh.naujavaspring.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.matveyelovskikh.naujavaspring.dto.UserDto;
import ru.matveyelovskikh.naujavaspring.service.UserService;

import java.util.List;

/**
 * Web контроллер для отображения
 * запросов пользователя
 */
@Controller
@RequestMapping("/custom/users/view")
public class UserWebController {

    private final UserService userService;

    /**
     * Внедрение зависимости UserService
     * @param userService сервис пользователя
     */
    @Autowired
    public UserWebController(UserService userService) {
        this.userService = userService;
    }

    /**
     * GET запрос на отображение списка всех пользователей
     * @param model модель данных для отображения
     * @return шаблон userList
     */
    @GetMapping(path = "/list")
    public String userListView(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }
}
