package ru.matveyelovskikh.naujavaspring.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.matveyelovskikh.naujavaspring.entity.RoleEntity;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;
import ru.matveyelovskikh.naujavaspring.entity.enums.UserRoles;
import ru.matveyelovskikh.naujavaspring.events.UserCreatedEvent;
import ru.matveyelovskikh.naujavaspring.repository.RoleRepository;
import ru.matveyelovskikh.naujavaspring.repository.UserCrud;

import java.util.List;

/**
 * Слушатель события присвоения роли
 */
@Service
public class RoleListener {

    private final RoleRepository roleRepository;
    private final UserCrud userCrud;

    /**
     * Внедрение RoleRepository, UserCrud
     * @param roleRepository репозиторий ролей
     * @param userCrud crud пользователя
     */
    @Autowired
    public RoleListener(RoleRepository roleRepository,
                        UserCrud userCrud) {
        this.roleRepository = roleRepository;
        this.userCrud = userCrud;
    }

    /**
     * Перехватить создание пользователя и присвоить
     * ему роль
     * @param event событие создания пользователя
     */
    @EventListener
    @Transactional(propagation = Propagation.REQUIRED)
    public void handleUserCreateEvent(UserCreatedEvent event) {
        UserEntity user = event.user();
        List<RoleEntity> roles = roleRepository.findAll();
        user.getRole().add(roles.get(UserRoles.ROLE_USER.ordinal()));

        userCrud.save(user);
    }
}
