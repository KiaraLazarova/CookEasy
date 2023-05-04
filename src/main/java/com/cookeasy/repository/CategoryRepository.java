package com.cookeasy.repository;

import com.cookeasy.model.entity.CategoryEntity;
import com.cookeasy.model.entity.enumeration.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByCategoryNameEnum(CategoryNameEnum categoryNameEnum);
}