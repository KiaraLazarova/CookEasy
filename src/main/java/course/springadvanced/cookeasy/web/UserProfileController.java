package course.springadvanced.cookeasy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserProfileController {
    @GetMapping(value = "/users/{id}/profile")
    public String retrieveProfilePage(@PathVariable(name = "id") String id) {
        return "profile";
    }
}