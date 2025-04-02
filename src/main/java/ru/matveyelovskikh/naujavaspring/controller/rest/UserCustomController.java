package ru.matveyelovskikh.naujavaspring.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.matveyelovskikh.naujavaspring.repository.UserCrud;

/**
 * Контроллер кастомного запроса в User
 */
@RestController
@RequestMapping("/custom/user")
public class UserCustomController {

    private final UserCrud userCrud;

    /**
     * Внедрение зависимости UserCrud
     * @param userCrud crud пользователя
     */
    @Autowired
    public UserCustomController(UserCrud userCrud) {
        this.userCrud = userCrud;
    }

    /**
     * GET запрос на получение информации о существовании пользователя
     * по его имени и почте
     * @param username имя пользователя
     * @param email почта пользователя
     * @return true - user существует, false - не существует
     */
    @GetMapping(path = "/exist")
    public ResponseEntity<Boolean> isExist(@RequestParam String username,
                                     @RequestParam String email) {
        boolean exist = userCrud.existsByUsernameAndEmail(username,
                email);
        return ResponseEntity.ok().body(exist);
    }
}
