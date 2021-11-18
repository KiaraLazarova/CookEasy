package course.springadvanced.cookeasy.model.service;

import course.springadvanced.cookeasy.model.entity.enumeration.GenderNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;

public class UserRegisterServiceModel {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private GenderNameEnum genderNameEnum;
    private LevelNameEnum levelNameEnum;

    public UserRegisterServiceModel() {
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