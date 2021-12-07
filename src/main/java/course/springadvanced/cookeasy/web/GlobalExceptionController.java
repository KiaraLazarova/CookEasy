package course.springadvanced.cookeasy.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class GlobalExceptionController implements ErrorController {
    @GetMapping(value = "/error")
    public String handleError(HttpServletRequest httpServletRequest) {
        String errorStatusCodeAttribute = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();
        int errorStatusCode = Integer.parseInt(errorStatusCodeAttribute);

        if(errorStatusCode == HttpStatus.FORBIDDEN.value()) return "403";

        if(errorStatusCode == HttpStatus.NOT_FOUND.value()) return "404";

        return "error";
    }
}