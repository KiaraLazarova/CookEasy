package course.springadvanced.cookeasy.web;

import course.springadvanced.cookeasy.model.view.UserProfileDetailsViewModel;
import course.springadvanced.cookeasy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserProfileController {
    private final UserService userService;

    @Autowired
    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/{id}/profile")
    public String retrieveProfilePage(@PathVariable(name = "id") Long id, Model model) {
        UserProfileDetailsViewModel userProfileDetailsViewModel = this.userService.getUserProfileDetails(id);
        model.addAttribute("userProfileDetailsViewModel", userProfileDetailsViewModel);

        return "profile";
    }
}