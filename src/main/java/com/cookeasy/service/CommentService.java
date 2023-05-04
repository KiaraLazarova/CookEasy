package com.cookeasy.service;

import com.cookeasy.model.entity.CommentEntity;
import com.cookeasy.model.service.CommentPostServiceModel;
import com.cookeasy.model.view.CommentAdminPanelViewModel;
import com.cookeasy.model.view.CommentDisplayViewModel;

import java.util.List;

public interface CommentService {
    void initializeComments();
    long getCountOfRecipeComments(Long recipeId);
    List<CommentDisplayViewModel> getCommentDisplayViewModels(Long recipeId);
    List<CommentEntity> findAllCommentsByRecipeIdAndApprovedTrue(Long recipeId);
    List<CommentEntity> findAllCommentsByRecipeId(Long recipeId);
    List<CommentEntity> findAllCommentsByAuthorId(Long authorId);
    void deleteComment(Long id);
    void postComment(String username, Long recipeId, CommentPostServiceModel commentPostServiceModel);
    List<CommentAdminPanelViewModel> getCommentAdminPanel();
    void approveComment(Long id);
    void archiveComment(Long id);
    void deleteArchivedComments();
}