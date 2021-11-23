package course.springadvanced.cookeasy.model.binding;

import course.springadvanced.cookeasy.model.entity.enumeration.GenderNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserProfileEditBindingModel {
    private String firstName;
    @NotBlank
    @Size(min = 5, max = 12)
    private String lastName;
    @NotNull
    private GenderNameEnum genderNameEnum;
    @NotNull
    private LevelNameEnum levelNameEnum;

    public UserProfileEditBindingModel() {
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