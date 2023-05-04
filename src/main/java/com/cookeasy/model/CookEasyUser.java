package com.cookeasy.model;

import com.cookeasy.model.entity.GenderEntity;
import com.cookeasy.model.entity.LevelEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;

@Getter
public class CookEasyUser extends User {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final GenderEntity genderEntity;
    private final LevelEntity levelEntity;

    public CookEasyUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
                        Long id, String firstName, String lastName, String email, GenderEntity genderEntity,
                        LevelEntity levelEntity) {
        super(username, password, authorities);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.genderEntity = genderEntity;
        this.levelEntity = levelEntity;
    }

    public CookEasyUser(String username, String password, boolean enabled, boolean accountNonExpired,
                        boolean credentialsNonExpired, boolean accountNonLocked,
                        Collection<? extends GrantedAuthority> authorities, Long id, String firstName,
                        String lastName, String email, GenderEntity genderEntity, LevelEntity levelEntity) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.genderEntity = genderEntity;
        this.levelEntity = levelEntity;
    }
}