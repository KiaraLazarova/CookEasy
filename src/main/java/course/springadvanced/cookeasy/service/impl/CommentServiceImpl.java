package course.springadvanced.cookeasy.service.impl;

import course.springadvanced.cookeasy.repository.CommentRepository;
import course.springadvanced.cookeasy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public long getCountOfRecipeComments(Long recipeId) {
        return this.commentRepository.findAllByRecipeEntityCount(recipeId);
    }
}