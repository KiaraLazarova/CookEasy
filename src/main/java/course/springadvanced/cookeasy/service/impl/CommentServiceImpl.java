package course.springadvanced.cookeasy.service.impl;

import course.springadvanced.cookeasy.model.entity.CommentEntity;
import course.springadvanced.cookeasy.model.entity.RecipeEntity;
import course.springadvanced.cookeasy.model.entity.UserEntity;
import course.springadvanced.cookeasy.model.service.CommentPostServiceModel;
import course.springadvanced.cookeasy.model.view.CommentAdminPanelViewModel;
import course.springadvanced.cookeasy.model.view.CommentDisplayViewModel;
import course.springadvanced.cookeasy.repository.CommentRepository;
import course.springadvanced.cookeasy.service.CommentService;
import course.springadvanced.cookeasy.service.RecipeService;
import course.springadvanced.cookeasy.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final RecipeService recipeService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, @Lazy RecipeService recipeService, UserService userService, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.recipeService = recipeService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initializeComments() {
        if(this.commentRepository.count() != 0) return;

        if(this.recipeService.getRecipeCount() != 3) return;

        if(this.userService.getUserCount() != 3) return;

        /* Initialize comment about beginner recipe entity posted by @ivan_lazarov */
        String beginnerRecipeCommentContent = """
                My recipe is AMAZING &#127851;!!!
                The result will amaze you.
                This homemade hot chocolate is super delicious and it is absolutely perfect for Christmas!
                You should definitely try it - it is super easy to make!
                Happy holidays &#65039;!!!
                X O X O &#127877;
                """;

        this.initializeComment(
                true,
                beginnerRecipeCommentContent,
                1L,
                "ivan_lazarov"
        );

        /* Initialize comment about intermediate recipe entity posted by @daniel_lazarov */
        String intermediateRecipeCommentContent = """
               My recipe is AMAZING &#127859;!!!
               The result will amaze you.
               These scrambled eggs are super delicious and they are absolutely perfect for your breakfast!
               You should definitely try them - they are not so hard to make!
               Happy holidays &#65039;!!!
               X O X O &#127877;
               """;

        this.initializeComment(
                true,
                intermediateRecipeCommentContent,
                2L,
                "daniel_lazarov"
        );

        /* Initialize comment about advanced recipe entity posted by @kiara_lazarova */
        String advancedRecipeCommentContent = """
               My recipe is AMAZING &#127856;!!!
               The result will amaze you.
               This vanilla cake is super delicious and it is absolutely perfect for your birthday party!
               You should definitely try it - it is hard to make but I promise that you won't regret it!
               Happy holidays &#65039;!!!
               X O X O &#127877;
               """;

        this.initializeComment(
                true,
                advancedRecipeCommentContent,
                3L,
                "kiara_lazarova"
        );
    }

    @Override
    public long getCountOfRecipeComments(Long recipeId) {
        return this.commentRepository.findAllByRecipeEntityCount(recipeId);
    }

    @Override
    public List<CommentDisplayViewModel> getCommentDisplayViewModels(Long recipeId) {
        return this.findAllCommentsByRecipeIdAndApprovedTrue(recipeId).stream().map(this::mapToCommentViewModel).collect(Collectors.toList());
    }

    @Override
    public List<CommentEntity> findAllCommentsByRecipeIdAndApprovedTrue(Long recipeId) {
        return this.commentRepository.findAllByRecipeEntityAndApprovedTrue(recipeId);
    }

    @Override
    public List<CommentEntity> findAllCommentsByRecipeId(Long recipeId) {
        return this.commentRepository.findAllByRecipeEntity(recipeId);
    }

    @Override
    public List<CommentEntity> findAllCommentsByAuthorId(Long authorId) {
        return this.commentRepository.findAllByAuthorEntity(authorId);
    }

    @Override
    public void deleteComment(Long id) {
        this.commentRepository.deleteById(id);
    }

    @Override
    public void postComment(String username, Long recipeId, CommentPostServiceModel commentPostServiceModel) {
        this.initializeComment(
                false,
                commentPostServiceModel.getContent(),
                recipeId,
                username
        );
    }

    @Override
    public List<CommentAdminPanelViewModel> getCommentAdminPanel() {
        return this.commentRepository.findAll()
                .stream()
                .map(this::mapToCommentAdminPanelViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public void approveComment(Long id) {
        CommentEntity comment = this.findCommentById(id);

        comment.setApproved(true);

        this.commentRepository.saveAndFlush(comment);
    }

    @Override
    public void archiveComment(Long id) {
        CommentEntity comment = this.findCommentById(id);

        comment.setArchived(true);

        this.commentRepository.saveAndFlush(comment);
    }

    private void initializeComment(boolean isApproved, String content, Long recipeId, String username) {
        CommentEntity comment = new CommentEntity();

        comment.setCreatedOn(LocalDateTime.now());

        comment.setApproved(isApproved);

        comment.setArchived(false);

        comment.setContent(content);

        RecipeEntity recipe = this.recipeService.findRecipeById(recipeId);
        comment.setRecipeEntity(recipe);

        UserEntity author = this.userService.findUserByUsername(username);
        comment.setAuthor(author);

        this.commentRepository.saveAndFlush(comment);
    }

    private CommentDisplayViewModel mapToCommentViewModel(CommentEntity comment) {
        CommentDisplayViewModel commentDisplayViewModel = this.modelMapper.map(comment, CommentDisplayViewModel.class);

        commentDisplayViewModel.setCreatedOn(comment.getCreatedOn().toLocalDate());

        commentDisplayViewModel.setAuthorUsername(comment.getAuthor().getUsername());

        commentDisplayViewModel.setAuthorGenderNameEnumName(comment.getAuthor().getGenderEntity().getGenderNameEnum().name().toLowerCase());

        return commentDisplayViewModel;
    }

    private CommentAdminPanelViewModel mapToCommentAdminPanelViewModel(CommentEntity comment) {
        CommentAdminPanelViewModel commentAdminPanelViewModel =
                this.modelMapper.map(comment, CommentAdminPanelViewModel.class);

        commentAdminPanelViewModel.setAuthorUsername(comment.getAuthor().getUsername());

        commentAdminPanelViewModel.setRecipeTitle(comment.getRecipeEntity().getTitle());

        commentAdminPanelViewModel.setCreatedOn(comment.getCreatedOn().toLocalDate());

        return commentAdminPanelViewModel;
    }

    private CommentEntity findCommentById(Long id) {
        //TODO add error handling - object not found exception
        return this.commentRepository.findById(id).get();
    }
}