package com.cookeasy.web;

import com.cookeasy.model.view.RecipeBriefDescriptionViewModel;
import com.cookeasy.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.security.Principal;
import java.util.List;

@Controller
public class UserCollectionController {
    private final RecipeService recipeService;

    @Autowired
    public UserCollectionController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PreAuthorize("@userServiceImpl.isProfileOwner(#principal.name, #id)")
    @GetMapping(value = "/users/{id}/collections/added")
    public String retrieveAddedRecipesPage(@PathVariable(name = "id") Long id, Model model, Principal principal) {
        List<RecipeBriefDescriptionViewModel> recipeBriefDescriptionViewModels =
                this.recipeService.getAddedRecipesBriefDescriptions(principal.getName());

        model.addAttribute("viewModels", recipeBriefDescriptionViewModels);

        return "recipes-added";
    }

    @PreAuthorize("@userServiceImpl.isProfileOwner(#principal.name, #id)")
    @GetMapping(value = "/users/{id}/collections/liked")
    public String retrieveLikedRecipesPage(@PathVariable(name = "id") Long id, Model model, Principal principal) {
        List<RecipeBriefDescriptionViewModel> recipeBriefDescriptionViewModels =
                this.recipeService.getLikedRecipesBriefDescriptions(principal.getName());

        model.addAttribute("viewModels", recipeBriefDescriptionViewModels);

        return "recipes-liked";
    }

    @PreAuthorize("@userServiceImpl.isProfileOwner(#principal.name, #id)")
    @GetMapping(value = "/users/{id}/collections/saved")
    public String retrieveSavedRecipesPage(@PathVariable(name = "id") Long id, Model model, Principal principal) {
        List<RecipeBriefDescriptionViewModel> recipeBriefDescriptionViewModels =
                this.recipeService.getSavedRecipesBriefDescriptions(principal.getName());

        model.addAttribute("viewModels", recipeBriefDescriptionViewModels);

        return "recipes-saved";
    }

    @PreAuthorize("@userServiceImpl.isProfileOwner(#principal.name, #id)")
    @GetMapping(value = "/users/{id}/collections/cooked")
    public String retrieveCookedRecipesPage(@PathVariable(name = "id") Long id, Model model, Principal principal) {
        List<RecipeBriefDescriptionViewModel> recipeBriefDescriptionViewModels =
                this.recipeService.getCookedRecipesBriefDescriptions(principal.getName());

        model.addAttribute("viewModels", recipeBriefDescriptionViewModels);

        return "recipes-cooked";
    }
}