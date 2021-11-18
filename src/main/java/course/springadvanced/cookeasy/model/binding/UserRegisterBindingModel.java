package course.springadvanced.cookeasy.model.binding;

import course.springadvanced.cookeasy.model.entity.enumeration.GenderNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;
import course.springadvanced.cookeasy.util.annotation.UniqueEmail;
import course.springadvanced.cookeasy.util.annotation.UniqueUsername;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {
    private String firstName;
    @NotBlank
    @Size(min = 5, max = 12)
    private String lastName;
    @NotBlank
    @UniqueUsername
    @Size(min = 5, max = 20)
    private String username;
    @NotBlank
    @UniqueEmail
    @Size(min = 5, max = 30)
    private String email;
    @NotBlank
    @Size(min = 5, max = 20)
    private String password;
    @NotBlank
    @Size(min = 5, max = 20)
    private String confirmPassword;
    @NotNull
    private GenderNameEnum genderNameEnum;
    @NotNull
    private LevelNameEnum levelNameEnum;

    public UserRegisterBindingModel() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public GenderNameEnum getGenderNameEnum() {
        return this.genderNameEnum;
    }

    public void setGenderNameEnum(GenderNameEnum genderNameEnum) {
        this.genderNameEnum = genderNameEnum;
    }

    public LevelNameEnum getLevelNameEnum() {
        return this.levelNameEnum;
    }

    public void setLevelNameEnum(LevelNameEnum levelNameEnum) {
        this.levelNameEnum = levelNameEnum;
    }
}