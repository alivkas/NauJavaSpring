package ru.matveyelovskikh.naujavaspring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matveyelovskikh.naujavaspring.dto.UserDto;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;
import ru.matveyelovskikh.naujavaspring.events.UserCreatedEvent;
import ru.matveyelovskikh.naujavaspring.exception.UserAlreadyExistException;
import ru.matveyelovskikh.naujavaspring.mapstruct.UserMapper;
import ru.matveyelovskikh.naujavaspring.repository.UserCrud;
import ru.matveyelovskikh.naujavaspring.service.UserService;

/**
 * Реализация сервиса пользователя
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserCrud userCrud;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * Внедрение зависимости UserCrud, PasswordEncoder, UserMapper,
     * ApplicationEventPublisher
     * @param userCrud crud операции над пользователем
     * @param userMapper маппер пользователя
     * @param passwordEncoder кодировщик пароля
     * @param applicationEventPublisher паблишер событий
     */
    @Autowired
    public UserServiceImpl(UserCrud userCrud,
                           UserMapper userMapper,
                           PasswordEncoder passwordEncoder,
                           ApplicationEventPublisher applicationEventPublisher) {
        this.userCrud = userCrud;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    @Override
    public void createUser(UserDto userDto) {
        if (userCrud.existsByUsernameAndEmail(
                userDto.username(), userDto.email())) {
            throw new UserAlreadyExistException(userDto.username(),
                    userDto.email());
        }
        UserEntity user = userMapper.toEntity(userDto, passwordEncoder);
        userCrud.save(user);

        applicationEventPublisher.publishEvent(new UserCreatedEvent(user));
    }
}
