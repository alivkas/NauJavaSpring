package ru.matveyelovskikh.naujavaspring.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.matveyelovskikh.naujavaspring.dto.UserDto;
import ru.matveyelovskikh.naujavaspring.entity.UserEntity;
import ru.matveyelovskikh.naujavaspring.events.UserCreatedEvent;
import ru.matveyelovskikh.naujavaspring.exception.UserAlreadyExistException;
import ru.matveyelovskikh.naujavaspring.mapstruct.UserMapper;
import ru.matveyelovskikh.naujavaspring.repository.UserCrud;
import ru.matveyelovskikh.naujavaspring.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Тест сервиса пользователей
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserCrud userCrud;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private UserServiceImpl userService;

    /**
     * Тестировать успешное создание пользователя
     */
    @Test
    public void createUserSuccessTest() {
        UserDto userDto = new UserDto(23L,
                "testUser",
                "password",
                "test@example.com",
                false,
                new ArrayList<>());
        UserEntity userEntity = new UserEntity();

        when(userCrud.existsByUsernameAndEmail(userDto.username(), userDto.email()))
                .thenReturn(false);
        when(userMapper.toEntity(userDto, passwordEncoder)).thenReturn(userEntity);

        userService.createUser(userDto);

        verify(userCrud).save(userEntity);
        verify(applicationEventPublisher).publishEvent(any(UserCreatedEvent.class));
    }

    /**
     * Тестировать получение исключения о существовании пользователя при его создании
     */
    @Test
    public void userAlreadyExistsThrowsExceptionTest() {
        UserDto userDto = new UserDto(23L,
                "existingUser",
                "password",
                "existing@example.com",
                false,
                new ArrayList<>());

        when(userCrud.existsByUsernameAndEmail(userDto.username(), userDto.email()))
                .thenReturn(true);

       UserAlreadyExistException exception =
               Assertions.assertThrows(UserAlreadyExistException.class,
                       () -> userService.createUser(userDto));

       String message = "Пользователь с таким именем existingUser и " +
               "почтой existing@example.com уже существует";

       Assertions.assertEquals(message, exception.getMessage());

       verify(userCrud, never()).save(any());
       verify(applicationEventPublisher, never()).publishEvent(any());
    }

    /**
     * Тестировать получение списка всех пользователей
     */
    @Test
    public void getAllUsersReturnsListTest() {
        UserDto userDto = new UserDto(23L,
                "testUser",
                "password",
                "test@example.com",
                false,
                new ArrayList<>());
        UserEntity userEntity = new UserEntity();

        when(userCrud.findAll()).thenReturn(Collections.singletonList(userEntity));
        when(userMapper.toDtoList(Collections.singletonList(userEntity)))
                .thenReturn(Collections.singletonList(userDto));

        List<UserDto> result = userService.getAllUsers();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(userDto, result.get(0));
    }

    /**
     * Тестировать случай получения списка всех пользователей,
     * когда их нет
     */
    @Test
    public void getAllUsersReturnsEmptyListTest() {
        when(userCrud.findAll()).thenReturn(Collections.emptyList());
        when(userMapper.toDtoList(Collections.emptyList()))
                .thenReturn(Collections.emptyList());

        List<UserDto> result = userService.getAllUsers();

        Assertions.assertTrue(result.isEmpty());
    }

    /**
     * Тестировать проверку на существуюшего пользователя,
     * когда он существует
     */
    @Test
    public void isUserExistsReturnsTrueTest() {
        String username = "existingUser";
        String email = "existing@example.com";

        when(userCrud.existsByUsernameAndEmail(username, email)).thenReturn(true);

        boolean result = userService.isUserExists(username, email);

        Assertions.assertTrue(result);
    }

    /**
     * Тестировать проверку на существуюшего пользователя,
     * когда он не существует
     */
    @Test
    public void isUserExistsReturnsFalse() {
        String username = "nonExistingUser";
        String email = "nonExisting@example.com";

        when(userCrud.existsByUsernameAndEmail(username, email)).thenReturn(false);

        boolean result = userService.isUserExists(username, email);

        Assertions.assertFalse(result);
    }
}
