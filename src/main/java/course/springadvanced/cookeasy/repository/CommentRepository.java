package course.springadvanced.cookeasy.repository;

import course.springadvanced.cookeasy.model.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    @Query(value = "SELECT COUNT(c) FROM CommentEntity AS c WHERE c.recipeEntity.id = :recipeId AND c.approved = TRUE AND c.archived = FALSE")
    long findAllByRecipeEntityCount(@Param(value = "recipeId") Long id);

    @Query(value = "SELECT c FROM CommentEntity AS c WHERE c.recipeEntity.id = :recipeId AND c.approved = TRUE AND c.archived = FALSE")
    List<CommentEntity> findAllByRecipeEntityAndApprovedTrue(@Param(value = "recipeId") Long recipeId);

    @Query(value = "SELECT c FROM CommentEntity AS c WHERE c.recipeEntity.id = :recipeId")
    List<CommentEntity> findAllByRecipeEntity(@Param(value = "recipeId") Long recipeId);

    @Query(value = "SELECT c FROM CommentEntity AS c WHERE c.author.id = :authorId")
    List<CommentEntity> findAllByAuthorEntity(@Param(value = "authorId") Long authorId);

    List<CommentEntity> findAllByArchivedTrue();
}