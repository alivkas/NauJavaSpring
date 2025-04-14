package ru.matveyelovskikh.naujavaspring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.matveyelovskikh.naujavaspring.exception.UserNotFoundException;
import ru.matveyelovskikh.naujavaspring.repository.UserCrud;

/**
 * Сервис для реализации UserDetailsService
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserCrud userCrud;

    /**
     * Внедрение зависимости UserCrud
     * @param userCrud crud пользователя
     */
    @Autowired
    public UserDetailServiceImpl(UserCrud userCrud) {
        this.userCrud = userCrud;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userCrud.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }
}
