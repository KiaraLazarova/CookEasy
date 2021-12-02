package course.springadvanced.cookeasy.service;

import course.springadvanced.cookeasy.model.entity.CommentEntity;
import course.springadvanced.cookeasy.model.view.CommentDisplayViewModel;
import java.util.List;

public interface CommentService {
    void initializeComments();
    long getCountOfRecipeComments(Long recipeId);
    List<CommentDisplayViewModel> getCommentDisplayViewModels(Long recipeId);
    List<CommentEntity> findAllCommentsByRecipeIdAndApprovedTrue(Long recipeId);
    List<CommentEntity> findAllCommentsByRecipeId(Long recipeId);
    List<CommentEntity> findAllCommentsByAuthorId(Long authorId);
    void deleteComment(Long id);
}