package course.springadvanced.cookeasy.model.service;

import course.springadvanced.cookeasy.model.entity.enumeration.GenderNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserProfileEditServiceModel {
    private String firstName;
    private String lastName;
    private GenderNameEnum genderNameEnum;
    private LevelNameEnum levelNameEnum;
}