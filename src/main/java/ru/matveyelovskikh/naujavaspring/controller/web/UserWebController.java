package ru.matveyelovskikh.naujavaspring.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;
import ru.matveyelovskikh.naujavaspring.repository.UserCrud;

import java.util.List;

/**
 * Web контроллер для отображения
 * запросов пользователя
 */
@Controller
@RequestMapping("/custom/users/view")
public class UserWebController {

    private final UserCrud userCrud;

    /**
     * Внедрение зависимости UserCrud
     * @param userCrud crud пользователя
     */
    @Autowired
    public UserWebController(UserCrud userCrud) {
        this.userCrud = userCrud;
    }

    /**
     * GET запрос на отображение списка всех пользователей
     * @param model модель данных для отображения
     * @return шаблон userList
     */
    @GetMapping(path = "/list")
    public String userListView(Model model) {
        List<UserEntity> users = (List<UserEntity>) userCrud.findAll();
        model.addAttribute("users", users);
        return "userList";
    }
}
