package ru.matveyelovskikh.naujavaspring.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.matveyelovskikh.naujavaspring.entity.base.BasicEntity;

import java.util.*;

/**
 * Сущность пользователя
 */
@Entity
@Table(name = "tbl_user")
public class UserEntity extends BasicEntity implements UserDetails {

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "is_email_verified")
    private Boolean isEmailVerified;
    @Column(name = "is_admin")
    private Boolean isAdmin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<EventsDayEntity> eventsDay = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<NotificationEntity> notification = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tbl_user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            }
    )
    private Set<RoleEntity> role = new HashSet<>();

    public UserEntity() {
    }

    /**
     * Конструктор UserEntity для инициализации полей
     * @param username имя пользователя
     * @param password пароль
     * @param email почта
     * @param isActive активен ли пользователь
     * @param isEmailVerified подтверждена ли почта
     * @param isAdmin админ ли пользователь
     */
    public UserEntity(String username,
                      String password,
                      String email,
                      Boolean isActive,
                      Boolean isEmailVerified,
                      Boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isActive = isActive;
        this.isEmailVerified = isEmailVerified;
        this.isAdmin = isAdmin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    /**
     * Получить имя пользователя
     * @return имя пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Установить имя пользователя
     * @param username имя пользователя
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Получить пароль
     * @return пароль
     */
    public String getPassword() {
        return password;
    }

    /**
     * Установить пароль
     * @param password пароль
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Получить почту
     * @return почта
     */
    public String getEmail() {
        return email;
    }

    /**
     * Установить почту
     * @param email почта
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Получить статус активности пользователя
     * @return активности пользователя
     */
    public Boolean getActive() {
        return isActive;
    }

    /**
     * Установить активность пользователя
     * @param active активности пользователя
     */
    public void setActive(Boolean active) {
        isActive = active;
    }

    /**
     * Получить статус подтверждения почты
     * @return подтверждение почты
     */
    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    /**
     * Установить подтверждение почты
     * @param emailVerified подтверждение почты
     */
    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    /**
     * Получить статус админства пользователя
     * @return админство пользователя
     */
    public Boolean getAdmin() {
        return isAdmin;
    }

    /**
     * Установить админство пользователя
     * @param admin админство пользователя
     */
    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    /**
     * Получить список событий дня пользователя
     * @return список событий дня пользователя
     */
    public List<EventsDayEntity> getEventsDay() {
        return eventsDay;
    }

    /**
     * Установить список событий дня пользователя
     * @param eventsDay список событий дня пользователя
     */
    public void setEventsDay(List<EventsDayEntity> eventsDay) {
        this.eventsDay = eventsDay;
    }

    /**
     * Получить уведомление пользователя
     * @return уведомление пользователя
     */
    public List<NotificationEntity> getNotification() {
        return notification;
    }

    /**
     * Установить уведомление пользователя
     * @param notification уведомление пользователя
     */
    public void setNotification(List<NotificationEntity> notification) {
        this.notification = notification;
    }

    /**
     * Получить множество ролей
     * @return множество ролей
     */
    public Set<RoleEntity> getRole() {
        return role;
    }

    /**
     * Установить множество ролей
     * @param role множество ролей
     */
    public void setRole(Set<RoleEntity> role) {
        this.role = role;
    }
}
