package com.cookeasy.web;

import com.cookeasy.model.binding.UserProfileEditBindingModel;
import com.cookeasy.model.service.UserProfileEditServiceModel;
import com.cookeasy.model.view.UserProfileDetailsViewModel;
import com.cookeasy.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserProfileController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserProfileController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("@userServiceImpl.isProfileOwner(#principal.name, #id)")
    @GetMapping(value = "/users/{id}/profile")
    public String retrieveProfilePage(@PathVariable(name = "id") Long id, Model model, Principal principal) {
        UserProfileDetailsViewModel userProfileDetailsViewModel = this.userService.getUserProfileDetails(id);

        model.addAttribute("viewModel", userProfileDetailsViewModel);

        return "profile";
    }

    @PreAuthorize("@userServiceImpl.isProfileOwner(#principal.name, #id)")
    @GetMapping(value = "/users/{id}/profile/edit")
    public String retrieveProfileEditPage(@PathVariable(name = "id") Long id, Model model, Principal principal) {
        UserProfileDetailsViewModel userProfileDetailsViewModel = this.userService.getUserProfileDetails(id);
        UserProfileEditBindingModel userProfileEditBindingModel = this.modelMapper.map(userProfileDetailsViewModel, UserProfileEditBindingModel.class);

        model.addAttribute("bindingModel", userProfileEditBindingModel);

        return "profile-edit";
    }

    @PreAuthorize("@userServiceImpl.isProfileOwner(#principal.name, #id)")
    @PatchMapping(value = "/users/{id}/profile/edit")
    public String editUserProfile(@PathVariable(name = "id") Long id,
                                  @Valid UserProfileEditBindingModel userProfileEditBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  Principal principal) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bindingModel", userProfileEditBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bindingModel", bindingResult);

            return "redirect:/users/" + id + "/profile/edit";
        }

        UserProfileEditServiceModel userProfileEditServiceModel = this.modelMapper.map(userProfileEditBindingModel, UserProfileEditServiceModel.class);

        this.userService.editUserProfile(id, userProfileEditServiceModel);

        return "redirect:/users/" + id + "/profile";
    }

    @PreAuthorize("@userServiceImpl.isProfileOwner(#principal.name, #id)")
    @DeleteMapping(value = "/users/{id}/profile/delete")
    public String deleteUserProfile(@PathVariable(name = "id") Long id, Principal principal) {
        this.userService.deleteUserProfile(id);

        return "redirect:/";
    }
}