package course.springadvanced.cookeasy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserRegisterController {
    @GetMapping(value = "/users/register")
    public String retrieveRegisterPage() {
        return "auth-register";
    }
}