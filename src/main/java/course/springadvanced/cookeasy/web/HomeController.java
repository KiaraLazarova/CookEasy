package course.springadvanced.cookeasy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String retrieveHomePage() {
        return "index";
    }
}