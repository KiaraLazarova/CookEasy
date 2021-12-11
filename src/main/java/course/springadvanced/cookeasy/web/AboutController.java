package course.springadvanced.cookeasy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
    @GetMapping(value = "/about")
    public String retrieveAboutPage() {
        return "about";
    }
}