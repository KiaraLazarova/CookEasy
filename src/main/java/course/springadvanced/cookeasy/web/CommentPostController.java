package course.springadvanced.cookeasy.web;

import course.springadvanced.cookeasy.model.binding.CommentPostBindingModel;
import course.springadvanced.cookeasy.model.service.CommentPostServiceModel;
import course.springadvanced.cookeasy.service.CommentService;
import course.springadvanced.cookeasy.service.RecipeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class CommentPostController {
    private final CommentService commentService;
    private final RecipeService recipeService;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentPostController(CommentService commentService, RecipeService recipeService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.recipeService = recipeService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute(value = "bindingModel")
    public CommentPostBindingModel getCommentPostBindingModel() {
        return new CommentPostBindingModel();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/recipes/{id}/details/comment")
    public String retrieveCommentPage(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("recipeId", id);
        model.addAttribute("recipeTitle", this.recipeService.getRecipeTitle(id));
        model.addAttribute("authorUsername", this.recipeService.getRecipeAuthorUsername(id));

        return "comment-post";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/recipes/{id}/details/comment")
    public String postComment(@PathVariable(name = "id") Long id,
                              @Valid CommentPostBindingModel commentPostBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Principal principal) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bindingModel", commentPostBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bindingModel", commentPostBindingModel);

            return "redirect:/recipes/" + id + "/details/comment";
        }

        CommentPostServiceModel commentPostServiceModel =
                this.modelMapper.map(commentPostBindingModel, CommentPostServiceModel.class);

        this.commentService.postComment(principal.getName(), id, commentPostServiceModel);

        return "redirect:/recipes/" + id + "/details";
    }
}