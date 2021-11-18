package course.springadvanced.cookeasy.web;

import course.springadvanced.cookeasy.model.binding.UserRegisterBindingModel;
import course.springadvanced.cookeasy.model.service.UserRegisterServiceModel;
import course.springadvanced.cookeasy.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

@Controller
public class UserRegisterController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRegisterController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute(value = "bindingModel")
    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute(value = "usernameOccupied")
    public boolean getUsernameOccupied() {
        return false;
    }

    @ModelAttribute(value = "emailOccupied")
    public boolean getEmailOccupied() {
        return false;
    }

    @GetMapping(value = "/users/register")
    public String retrieveRegisterPage() {
        return "auth-register";
    }

    @PostMapping(value = "/users/register")
    public String registerAndLoginUser(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            if(this.userService.isUsernameOccupied(userRegisterBindingModel.getUsername()))
                redirectAttributes.addFlashAttribute("usernameOccupied", true);

            if(this.userService.isEmailOccupied(userRegisterBindingModel.getEmail()))
                redirectAttributes.addFlashAttribute("emailOccupied", true);

            redirectAttributes.addFlashAttribute("bindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bindingModel", bindingResult);

            return "redirect:/users/register";
        }

        UserRegisterServiceModel userRegisterServiceModel = this.modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);

        this.userService.registerAndLoginUser(userRegisterServiceModel);

        return "redirect:/";
    }
}