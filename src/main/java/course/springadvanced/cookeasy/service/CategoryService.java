package course.springadvanced.cookeasy.service;

import course.springadvanced.cookeasy.model.entity.CategoryEntity;
import course.springadvanced.cookeasy.model.entity.enumeration.CategoryNameEnum;

public interface CategoryService {
    void initializeCategories();
    CategoryEntity findCategoryByCategoryName(CategoryNameEnum categoryNameEnum);
}