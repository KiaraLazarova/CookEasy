package course.springadvanced.cookeasy.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuoteController {
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/quotes")
    public String retrieveQuotesPage() {
        return "quotes";
    }
}