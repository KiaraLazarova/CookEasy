package course.springadvanced.cookeasy.service.impl;

import course.springadvanced.cookeasy.model.CookEasyUser;
import course.springadvanced.cookeasy.model.entity.UserEntity;
import course.springadvanced.cookeasy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CookEasyUserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CookEasyUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s not found!", username)));

        return this.mapToUserDetails(user);
    }

    private UserDetails mapToUserDetails(UserEntity user) {
        Set<GrantedAuthority> grantedAuthorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(String.format("ROLE_%s", role.getRoleNameEnum().name())))
                .collect(Collectors.toSet());

        return new CookEasyUser(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getGenderEntity(),
                user.getLevelEntity(),
                user.getAddedRecipes(),
                user.getLikedRecipes(),
                user.getSavedRecipes(),
                user.getCookedRecipes(),
                user.getCommentedRecipes()
        );
    }
}