package course.springadvanced.cookeasy.model;

import course.springadvanced.cookeasy.model.entity.GenderEntity;
import course.springadvanced.cookeasy.model.entity.LevelEntity;
import course.springadvanced.cookeasy.model.entity.RecipeEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;
import java.util.List;

public class CookEasyUser extends User {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final GenderEntity genderEntity;
    private final LevelEntity levelEntity;
    private final List<RecipeEntity> addedRecipes;
    private final List<RecipeEntity> likedRecipes;
    private final List<RecipeEntity> savedRecipes;
    private final List<RecipeEntity> cookedRecipes;
    private final List<RecipeEntity> commentedRecipes;

    public CookEasyUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
                        Long id, String firstName, String lastName, String email, GenderEntity genderEntity,
                        LevelEntity levelEntity, List<RecipeEntity> addedRecipes, List<RecipeEntity> likedRecipes,
                        List<RecipeEntity> savedRecipes, List<RecipeEntity> cookedRecipes, List<RecipeEntity> commentedRecipes) {
        super(username, password, authorities);
        this.id = id;
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

    public CookEasyUser(String username, String password, boolean enabled, boolean accountNonExpired,
                        boolean credentialsNonExpired, boolean accountNonLocked,
                        Collection<? extends GrantedAuthority> authorities, Long id, String firstName,
                        String lastName, String email, GenderEntity genderEntity, LevelEntity levelEntity,
                        List<RecipeEntity> addedRecipes, List<RecipeEntity> likedRecipes, List<RecipeEntity> savedRecipes,
                        List<RecipeEntity> cookedRecipes, List<RecipeEntity> commentedRecipes) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
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

    public Long getId() {
        return this.id;
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

    public List<RecipeEntity> getAddedRecipes() {
        return this.addedRecipes;
    }

    public List<RecipeEntity> getLikedRecipes() {
        return this.likedRecipes;
    }

    public List<RecipeEntity> getSavedRecipes() {
        return this.savedRecipes;
    }

    public List<RecipeEntity> getCookedRecipes() {
        return this.cookedRecipes;
    }

    public List<RecipeEntity> getCommentedRecipes() {
        return this.commentedRecipes;
    }
}