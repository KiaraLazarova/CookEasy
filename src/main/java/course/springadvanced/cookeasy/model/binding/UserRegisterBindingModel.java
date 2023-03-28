package course.springadvanced.cookeasy.model.binding;

import course.springadvanced.cookeasy.model.entity.enumeration.GenderNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;
import course.springadvanced.cookeasy.util.annotation.UniqueEmail;
import course.springadvanced.cookeasy.util.annotation.UniqueUsername;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
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
    @Email
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
}