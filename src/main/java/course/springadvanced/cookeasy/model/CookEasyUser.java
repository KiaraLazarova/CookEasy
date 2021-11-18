package course.springadvanced.cookeasy.model;

import course.springadvanced.cookeasy.model.entity.GenderEntity;
import course.springadvanced.cookeasy.model.entity.LevelEntity;
import course.springadvanced.cookeasy.model.entity.RecipeEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;
import java.util.Set;

public class CookEasyUser extends User {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final GenderEntity genderEntity;
    private final LevelEntity levelEntity;
    private final Set<RecipeEntity> addedRecipes;
    private final Set<RecipeEntity> likedRecipes;
    private final Set<RecipeEntity> savedRecipes;
    private final Set<RecipeEntity> cookedRecipes;
    private final Set<RecipeEntity> commentedRecipes;

    public CookEasyUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String firstName, String lastName, String email, GenderEntity genderEntity, LevelEntity levelEntity, Set<RecipeEntity> addedRecipes, Set<RecipeEntity> likedRecipes, Set<RecipeEntity> savedRecipes, Set<RecipeEntity> cookedRecipes, Set<RecipeEntity> commentedRecipes) {
        super(username, password, authorities);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.genderEntity = genderEntity;
        this.levelEntity = levelEntity;
        this.addedRecipes = addedRecipes;
        this.likedRecipes = likedRecipes;
        this.savedRecipes = savedRecipes;
        this.cookedRecipes = cookedRecipes;
        this.commentedRecipes = commentedRecipes;
    }

    public CookEasyUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String firstName, String lastName, String email, GenderEntity genderEntity, LevelEntity levelEntity, Set<RecipeEntity> addedRecipes, Set<RecipeEntity> likedRecipes, Set<RecipeEntity> savedRecipes, Set<RecipeEntity> cookedRecipes, Set<RecipeEntity> commentedRecipes) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.genderEntity = genderEntity;
        this.levelEntity = levelEntity;
        this.addedRecipes = addedRecipes;
        this.likedRecipes = likedRecipes;
        this.savedRecipes = savedRecipes;
        this.cookedRecipes = cookedRecipes;
        this.commentedRecipes = commentedRecipes;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public GenderEntity getGenderEntity() {
        return this.genderEntity;
    }

    public LevelEntity getLevelEntity() {
        return this.levelEntity;
    }
}