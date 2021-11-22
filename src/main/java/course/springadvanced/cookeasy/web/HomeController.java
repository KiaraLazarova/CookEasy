package course.springadvanced.cookeasy.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String retrieveHomePage(@AuthenticationPrincipal UserDetails user) {
        if(user == null) return "index";
        //TODO display recipes from database
        return "home";
    }
}