package course.springadvanced.cookeasy.web;

import course.springadvanced.cookeasy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserAdminPanelController {
    private final UserService userService;

    @Autowired
    public UserAdminPanelController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/users")
    public String retrieveUsersPage(Model model) {
        model.addAttribute("viewModels", this.userService.getUserAdminPanel());

        return "users-admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(value = "/users/{id}/make-admin")
    public String makeUserAdmin(@PathVariable(name = "id") Long id) {
        this.userService.makeUserAdmin(id);

        return "redirect:/users";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/users/{id}/delete")
    public String deleteUserProfile(@PathVariable(name = "id") Long id) {
        this.userService.deleteUserAdminPanel(id);

        return "redirect:/users";
    }
}