package course.springadvanced.cookeasy.util;


import course.springadvanced.cookeasy.service.UserService;
import course.springadvanced.cookeasy.util.annotation.UniqueUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private final UserService userService;

    @Autowired
    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !username.trim().equals("") && !this.userService.isUsernameOccupied(username);
    }
}
