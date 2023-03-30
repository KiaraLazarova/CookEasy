package course.springadvanced.cookeasy.model.view;

import course.springadvanced.cookeasy.model.entity.GenderEntity;
import course.springadvanced.cookeasy.model.entity.LevelEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAdminPanelViewModel {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private GenderEntity genderEntity;
    private LevelEntity levelEntity;
    private boolean admin;
}