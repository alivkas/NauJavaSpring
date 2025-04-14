package ru.matveyelovskikh.naujavaspring.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import ru.matveyelovskikh.naujavaspring.entity.base.BasicEntity;
import ru.matveyelovskikh.naujavaspring.entity.enums.UserRoles;

import java.util.HashSet;
import java.util.Set;

/**
 * Сущность роли пользователя
 */
@Entity
@Table(name = "tbl_role")
public class RoleEntity extends BasicEntity implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private UserRoles role;

    @ManyToMany(mappedBy = "role")
    private Set<UserEntity> user = new HashSet<>();

    @Override
    public String getAuthority() {
        return role.name();
    }

    /**
     * Получить роль
     * @return роль
     */
    public UserRoles getRole() {
        return role;
    }

    /**
     * Установить роль
     * @param role роль
     */
    public void setRole(UserRoles role) {
        this.role = role;
    }

    /**
     * Получить множество ролей
     * @return множество ролей
     */
    public Set<UserEntity> getUser() {
        return user;
    }

    /**
     * Установить множество ролей
     * @param user множество ролей
     */
    public void setUser(Set<UserEntity> user) {
        this.user = user;
    }
}
