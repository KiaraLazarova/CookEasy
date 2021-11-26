package course.springadvanced.cookeasy.model.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private GenderEntity genderEntity;
    private LevelEntity levelEntity;
    private List<RoleEntity> roles = new ArrayList<>();
    private List<RecipeEntity> addedRecipes = new ArrayList<>();
    private List<RecipeEntity> likedRecipes = new ArrayList<>();
    private List<RecipeEntity> savedRecipes = new ArrayList<>();
    private List<RecipeEntity> cookedRecipes = new ArrayList<>();
    private List<RecipeEntity> commentedRecipes = new ArrayList<>();

    public UserEntity() {
    }

    @Column(name = "username", length = 20, nullable = false, unique = true)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", length = 12, nullable = false)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", length = 30, nullable = false, unique = true)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    public GenderEntity getGenderEntity() {
        return this.genderEntity;
    }

    public void setGenderEntity(GenderEntity genderEntity) {
        this.genderEntity = genderEntity;
    }

    @ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "id")
    public LevelEntity getLevelEntity() {
        return this.levelEntity;
    }

    public void setLevelEntity(LevelEntity levelEntity) {
        this.levelEntity = levelEntity;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    public List<RoleEntity> getRoles() {
        return this.roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    @OneToMany(mappedBy = "author", targetEntity = RecipeEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<RecipeEntity> getAddedRecipes() {
        return this.addedRecipes;
    }

    public void setAddedRecipes(List<RecipeEntity> addedRecipes) {
        this.addedRecipes = addedRecipes;
    }

    @OneToMany(mappedBy = "author", targetEntity = RecipeEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<RecipeEntity> getLikedRecipes() {
        return likedRecipes;
    }

    public void setLikedRecipes(List<RecipeEntity> likedRecipes) {
        this.likedRecipes = likedRecipes;
    }

    @OneToMany(mappedBy = "author", targetEntity = RecipeEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<RecipeEntity> getSavedRecipes() {
        return this.savedRecipes;
    }

    public void setSavedRecipes(List<RecipeEntity> savedRecipes) {
        this.savedRecipes = savedRecipes;
    }

    @OneToMany(mappedBy = "author", targetEntity = RecipeEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<RecipeEntity> getCookedRecipes() {
        return this.cookedRecipes;
    }

    public void setCookedRecipes(List<RecipeEntity> cookedRecipes) {
        this.cookedRecipes = cookedRecipes;
    }

    @OneToMany(mappedBy = "author", targetEntity = RecipeEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<RecipeEntity> getCommentedRecipes() {
        return this.commentedRecipes;
    }

    public void setCommentedRecipes(List<RecipeEntity> commentedRecipes) {
        this.commentedRecipes = commentedRecipes;
    }
}