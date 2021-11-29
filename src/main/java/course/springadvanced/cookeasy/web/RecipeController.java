package course.springadvanced.cookeasy.web;

import course.springadvanced.cookeasy.model.binding.RecipeAddBindingModel;
import course.springadvanced.cookeasy.model.service.RecipeAddServiceModel;
import course.springadvanced.cookeasy.model.view.RecipeBriefDescriptionViewModel;
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
    public String retrieveRecipeDetailsPage(@PathVariable(name = "id") Long id) {
        //TODO create recipe details template + edit, delete buttons (owner, admin) - delete button as form
        //TODO create recipe details view model - validate
        //TODO create get recipe details method in recipe service
        //TODO implement get recipe details method - return type recipe details view model
        //TODO invoke get recipe details method in controller
        //TODO add template form method attributes - th:action, th:method (get), th:field
        return null;
    }

    @PatchMapping(value = "/recipes/{id}/details/edit")
    public String editRecipe(@PathVariable(name = "id") Long id) {
        //TODO create recipe edit binding model - validate
        //TODO create recipe edit service model
        //TODO create edit recipe method in recipe service
        //TODO implement edit recipe method in recipe service - return type void
        //TODO pass recipe edit binding model as parameter to controller method
        //TODO validate recipe edit binding model
        //TODO map recipe edit binding model to recipe edit service model
        //TODO pass recipe edit service model as parameter to edit recipe method in recipe service
        //TODO redirect to /recipes/{id}/details
        //TODO add template form method attributes - th:action, th:method (patch), th:field
        return null;
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