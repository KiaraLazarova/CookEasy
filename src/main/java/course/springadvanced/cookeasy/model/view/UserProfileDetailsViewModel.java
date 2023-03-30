package course.springadvanced.cookeasy.model.view;

import course.springadvanced.cookeasy.model.entity.GenderEntity;
import course.springadvanced.cookeasy.model.entity.LevelEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserProfileDetailsViewModel {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private GenderEntity genderEntity;
    private LevelEntity levelEntity;
}