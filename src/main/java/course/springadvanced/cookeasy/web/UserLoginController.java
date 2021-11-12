package course.springadvanced.cookeasy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserLoginController {
    @GetMapping(value = "/users/login")
    public String retrieveLoginPage() {
        return "auth-login";
    }
}