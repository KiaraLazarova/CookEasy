package com.cookeasy.service;

import com.cookeasy.model.entity.CategoryEntity;
import com.cookeasy.model.entity.enumeration.CategoryNameEnum;

public interface CategoryService {
    void initializeCategories();
    CategoryEntity findCategoryByCategoryName(CategoryNameEnum categoryNameEnum);
}