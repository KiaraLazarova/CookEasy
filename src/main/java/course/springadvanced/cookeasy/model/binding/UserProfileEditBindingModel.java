package course.springadvanced.cookeasy.model.binding;

import course.springadvanced.cookeasy.model.entity.enumeration.GenderNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserProfileEditBindingModel {
    private String firstName;
    @NotBlank
    @Size(min = 5, max = 12)
    private String lastName;
    @NotNull
    private GenderNameEnum genderNameEnum;
    @NotNull
    private LevelNameEnum levelNameEnum;
}