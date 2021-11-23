package course.springadvanced.cookeasy.model.service;

import course.springadvanced.cookeasy.model.entity.enumeration.GenderNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;

public class UserProfileEditServiceModel {
    private String firstName;
    private String lastName;
    private GenderNameEnum genderNameEnum;
    private LevelNameEnum levelNameEnum;

    public UserProfileEditServiceModel() {
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