package course.springadvanced.cookeasy.model.service;

import course.springadvanced.cookeasy.model.entity.enumeration.GenderNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterServiceModel {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private GenderNameEnum genderNameEnum;
    private LevelNameEnum levelNameEnum;
}