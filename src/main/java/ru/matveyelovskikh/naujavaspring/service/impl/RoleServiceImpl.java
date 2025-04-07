package ru.matveyelovskikh.naujavaspring.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matveyelovskikh.naujavaspring.entity.RoleEntity;
import ru.matveyelovskikh.naujavaspring.entity.enums.UserRoles;
import ru.matveyelovskikh.naujavaspring.repository.RoleRepository;
import ru.matveyelovskikh.naujavaspring.service.RoleService;

/**
 * Реализация сервиса ролей
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    /**
     * Внедрение RoleRepository
     * @param roleRepository репозиторий ролей
     */
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    @PostConstruct
    @Override
    public void init() {
        if (roleRepository.count() == 0) {
            RoleEntity adminRole = new RoleEntity();
            adminRole.setRole(UserRoles.ROLE_ADMIN);
            roleRepository.save(adminRole);

            RoleEntity userRole = new RoleEntity();
            userRole.setRole(UserRoles.ROLE_USER);
            roleRepository.save(userRole);
        }
    }
}
