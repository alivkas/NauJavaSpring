package ru.matveyelovskikh.naujavaspring.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.matveyelovskikh.naujavaspring.entity.RoleEntity;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;
import ru.matveyelovskikh.naujavaspring.entity.enums.UserRoles;
import ru.matveyelovskikh.naujavaspring.repository.RoleRepository;
import ru.matveyelovskikh.naujavaspring.repository.UserCrud;

import java.util.List;

/**
 * Слушатель события запуска приложения для
 * создания пользователя с ролью админ
 */
@Service
public class AdminCreateListener {

    private final PasswordEncoder passwordEncoder;
    private final UserCrud userCrud;
    private final RoleRepository roleRepository;

    private final static Logger LOG = LoggerFactory.getLogger(AdminCreateListener.class);

    /**
     * Внедрение PasswordEncoder, UserCrud,
     * RoleRepository
     * @param passwordEncoder кодировка пароля
     * @param userCrud crud пользователя
     * @param roleRepository репозиторий ролей
     */
    @Autowired
    public AdminCreateListener(PasswordEncoder passwordEncoder,
                               UserCrud userCrud,
                               RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userCrud = userCrud;
        this.roleRepository = roleRepository;
    }

    /**
     * Перехватить запуск приложения создать пользователя
     * с ролью админ
     */
    @EventListener(ApplicationReadyEvent.class)
    @Transactional(propagation = Propagation.REQUIRED)
    public void handleApplicationReadyEvent() {
        userCrud.findByUsername("admin")
                .orElseGet(() -> {
                    UserEntity user = new UserEntity(
                            "admin",
                            passwordEncoder.encode("admin"),
                            "admin@mail.ru",
                            Boolean.TRUE,
                            Boolean.TRUE,
                            Boolean.TRUE
                    );
                    List<RoleEntity> roles = roleRepository.findAll();
                    user.getRole().add(roles.get(UserRoles.ROLE_ADMIN.ordinal()));
                    LOG.info("""
                            Создание админа прошло успешно
                            Логин: admin
                            Пароль: admin
                            """);

                    return userCrud.save(user);
                });
        LOG.info("""
                Админ уже существует
                """);
    }
}
