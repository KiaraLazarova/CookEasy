package course.springadvanced.cookeasy.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private GenderEntity genderEntity;
    private RoleEntity roleEntity;
    private LevelEntity levelEntity;
    private Set<RecipeEntity> addedRecipes;
    private Set<RecipeEntity> likedRecipes;
    private Set<RecipeEntity> savedRecipes;
    private Set<RecipeEntity> cookedRecipes;
    private Set<RecipeEntity> commentedRecipes;

    public UserEntity() {
    }

    @Column(name = "username", nullable = false, unique = true)
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

    @Column(name = "last_name", nullable = false)
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

    @Column(name = "email", nullable = false, unique = true)
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
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    public RoleEntity getRoleEntity() {
        return this.roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    @ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "id")
    public LevelEntity getLevelEntity() {
        return this.levelEntity;
    }

    public void setLevelEntity(LevelEntity levelEntity) {
        this.levelEntity = levelEntity;
    }

    @OneToMany(mappedBy = "author", targetEntity = RecipeEntity.class, fetch = FetchType.EAGER)
    public Set<RecipeEntity> getAddedRecipes() {
        return this.addedRecipes;
    }

    public void setAddedRecipes(Set<RecipeEntity> addedRecipes) {
        this.addedRecipes = addedRecipes;
    }

    @OneToMany(mappedBy = "author", targetEntity = RecipeEntity.class, fetch = FetchType.EAGER)
    public Set<RecipeEntity> getLikedRecipes() {
        return likedRecipes;
    }

    public void setLikedRecipes(Set<RecipeEntity> likedRecipes) {
        this.likedRecipes = likedRecipes;
    }

    @OneToMany(mappedBy = "author", targetEntity = RecipeEntity.class, fetch = FetchType.EAGER)
    public Set<RecipeEntity> getSavedRecipes() {
        return this.savedRecipes;
    }

    public void setSavedRecipes(Set<RecipeEntity> savedRecipes) {
        this.savedRecipes = savedRecipes;
    }

    @OneToMany(mappedBy = "author", targetEntity = RecipeEntity.class, fetch = FetchType.EAGER)
    public Set<RecipeEntity> getCookedRecipes() {
        return this.cookedRecipes;
    }

    public void setCookedRecipes(Set<RecipeEntity> cookedRecipes) {
        this.cookedRecipes = cookedRecipes;
    }

    @OneToMany(mappedBy = "author", targetEntity = RecipeEntity.class, fetch = FetchType.EAGER)
    public Set<RecipeEntity> getCommentedRecipes() {
        return this.commentedRecipes;
    }

    public void setCommentedRecipes(Set<RecipeEntity> commentedRecipes) {
        this.commentedRecipes = commentedRecipes;
    }
}