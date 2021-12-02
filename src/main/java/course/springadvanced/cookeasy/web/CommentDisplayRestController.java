package course.springadvanced.cookeasy.web;

import course.springadvanced.cookeasy.model.view.CommentDisplayViewModel;
import course.springadvanced.cookeasy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CommentDisplayRestController {
    private final CommentService commentService;

    @Autowired
    public CommentDisplayRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/api/{recipeId}/comments")
    public ResponseEntity<List<CommentDisplayViewModel>> retrieveComments(@PathVariable(name = "recipeId") Long recipeId) {
        List<CommentDisplayViewModel> commentDisplayViewModels = this.commentService.getCommentDisplayViewModels(recipeId);

        return ResponseEntity.ok(commentDisplayViewModels);
    }
}