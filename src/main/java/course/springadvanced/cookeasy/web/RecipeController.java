package course.springadvanced.cookeasy.web;

import course.springadvanced.cookeasy.model.binding.RecipeAddBindingModel;
import course.springadvanced.cookeasy.model.binding.RecipeEditBindingModel;
import course.springadvanced.cookeasy.model.service.RecipeAddServiceModel;
import course.springadvanced.cookeasy.model.service.RecipeEditServiceModel;
import course.springadvanced.cookeasy.model.view.RecipeBriefDescriptionViewModel;
import course.springadvanced.cookeasy.model.view.RecipeDetailsViewModel;
import course.springadvanced.cookeasy.service.RecipeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class RecipeController {
    private final RecipeService recipeService;
    private final ModelMapper modelMapper;

    @Autowired
    public RecipeController(RecipeService recipeService, ModelMapper modelMapper) {
        this.recipeService = recipeService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute(value = "bindingModel")
    public RecipeAddBindingModel getRecipeAddBindingModel() {
        return new RecipeAddBindingModel();
    }

    @ModelAttribute(value = "titleOccupied")
    public boolean getTitleOccupied() {
        return false;
    }

    @GetMapping(value = "/recipes")
    public String retrieveRecipesPage(Model model, Principal principal) {
        List<RecipeBriefDescriptionViewModel> recipeBriefDescriptionViewModels =
                this.recipeService.getRecipesBriefDescriptions(principal.getName());

        model.addAttribute("viewModels", recipeBriefDescriptionViewModels);

        return "recipes";
    }

    @GetMapping(value = "/recipes/add")
    public String retrieveRecipeAddPage() {
        return "recipe-add";
    }

    @PostMapping(value = "/recipes/add")
    public String addRecipe(@Valid RecipeAddBindingModel recipeAddBindingModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            Principal principal) {
        if(bindingResult.hasErrors()) {
            if(this.recipeService.isTitleOccupied(recipeAddBindingModel.getTitle()))
                redirectAttributes.addFlashAttribute("titleOccupied", true);

            redirectAttributes.addFlashAttribute("bindingModel", recipeAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bindingModel", bindingResult);

            return "redirect:/recipes/add";
        }

        RecipeAddServiceModel recipeAddServiceModel = this.modelMapper.map(recipeAddBindingModel, RecipeAddServiceModel.class);

        this.recipeService.addRecipe(principal.getName(), recipeAddServiceModel);

        return "redirect:/recipes";
    }

    @GetMapping(value = "/recipes/{id}/details")
    public String retrieveRecipeDetailsPage(@PathVariable(name = "id") Long id, Model model, Principal principal) {
        RecipeDetailsViewModel recipeDetailsViewModel = this.recipeService.getRecipeDetails(id);

        model.addAttribute("viewModel", recipeDetailsViewModel);

        boolean isRecipeLiked = this.recipeService.isRecipeLiked(principal.getName(), id);

        model.addAttribute("isRecipeLiked", isRecipeLiked);

        boolean isRecipeSaved = this.recipeService.isRecipeSaved(principal.getName(), id);

        model.addAttribute("isRecipeSaved", isRecipeSaved);

        boolean isRecipeCooked = this.recipeService.isRecipeCooked(principal.getName(), id);

        model.addAttribute("isRecipeCooked", isRecipeCooked);

        return "recipe-details";
    }

    @PatchMapping(value = "/recipes/{id}/details/like")
    public String likeRecipe(@PathVariable Long id, Principal principal) {
        this.recipeService.likeRecipe(principal.getName(), id);

        return "redirect:/recipes/" + id + "/details";
    }

    @PatchMapping(value = "/recipes/{id}/details/save")
    public String saveRecipe(@PathVariable Long id, Principal principal) {
        this.recipeService.saveRecipe(principal.getName(), id);

        return "redirect:/recipes/" + id + "/details";
    }

    @PatchMapping(value = "/recipes/{id}/details/cook")
    public String cookRecipe(@PathVariable Long id, Principal principal) {
        this.recipeService.cookRecipe(principal.getName(), id);

        return "redirect:/recipes/" + id + "/details";
    }

    @GetMapping(value = "/recipes/{id}/details/edit")
    public String retrieveRecipeEditPage(@PathVariable(name = "id") Long id, Model model) {
        RecipeDetailsViewModel recipeDetailsViewModel = this.recipeService.getRecipeDetails(id);
        RecipeEditBindingModel recipeEditBindingModel = this.modelMapper.map(recipeDetailsViewModel, RecipeEditBindingModel.class);

        model.addAttribute("editBindingModel", recipeEditBindingModel);

        return "recipe-edit";
    }

    @PatchMapping(value = "/recipes/{id}/details/edit")
    public String editRecipe(@PathVariable(name = "id") Long id,
                             @Valid RecipeEditBindingModel recipeEditBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("editBindingModel", recipeEditBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editBindingModel", bindingResult);

            return "redirect:/recipes/" + id + "/details/edit";
        }

        RecipeEditServiceModel recipeEditServiceModel = this.modelMapper.map(recipeEditBindingModel, RecipeEditServiceModel.class);

        this.recipeService.editRecipe(id, recipeEditServiceModel);

        return "redirect:/recipes/" + id + "/details";
    }

    @DeleteMapping(value = "/recipes/{id}/details/delete")
    public String deleteRecipe(@PathVariable(name = "id") Long id) {
        //TODO create delete recipe method in recipe service
        //TODO implement delete recipe method in recipe service - return type void
        //TODO redirect to /recipes
        //TODO add template form method attributes - th:action, th:method (delete)
        return null;
    }
}