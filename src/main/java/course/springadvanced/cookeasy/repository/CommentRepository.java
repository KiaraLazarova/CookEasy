package course.springadvanced.cookeasy.repository;

import course.springadvanced.cookeasy.model.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    @Query(value = "SELECT COUNT(c) FROM CommentEntity AS c WHERE c.recipeEntity.id = :recipeId AND c.approved = TRUE")
    long findAllByRecipeEntityCount(@Param(value = "recipeId") Long id);
}