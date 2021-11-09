package course.springadvanced.finalproject.service.impl;

import course.springadvanced.finalproject.model.entity.CategoryEntity;
import course.springadvanced.finalproject.model.entity.enumeration.CategoryNameEnum;
import course.springadvanced.finalproject.repository.CategoryRepository;
import course.springadvanced.finalproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initializeCategories() {
        if(this.categoryRepository.count() != 0) return;

        CategoryEntity salad = new CategoryEntity();
        salad.setCategoryNameEnum(CategoryNameEnum.SALAD);

        this.categoryRepository.saveAndFlush(salad);

        CategoryEntity starter = new CategoryEntity();
        starter.setCategoryNameEnum(CategoryNameEnum.STARTER);

        this.categoryRepository.saveAndFlush(starter);

        CategoryEntity sandwich = new CategoryEntity();
        sandwich.setCategoryNameEnum(CategoryNameEnum.SANDWICH);

        this.categoryRepository.saveAndFlush(sandwich);

        CategoryEntity soup = new CategoryEntity();
        soup.setCategoryNameEnum(CategoryNameEnum.SOUP);

        this.categoryRepository.saveAndFlush(soup);

        CategoryEntity meat = new CategoryEntity();
        meat.setCategoryNameEnum(CategoryNameEnum.MEAT);

        this.categoryRepository.saveAndFlush(meat);

        CategoryEntity pasta = new CategoryEntity();
        pasta.setCategoryNameEnum(CategoryNameEnum.PASTA);

        this.categoryRepository.saveAndFlush(pasta);

        CategoryEntity bread = new CategoryEntity();
        bread.setCategoryNameEnum(CategoryNameEnum.BREAD);

        this.categoryRepository.saveAndFlush(bread);

        CategoryEntity drink = new CategoryEntity();
        drink.setCategoryNameEnum(CategoryNameEnum.DRINK);

        this.categoryRepository.saveAndFlush(drink);

        CategoryEntity dessert = new CategoryEntity();
        dessert.setCategoryNameEnum(CategoryNameEnum.DESSERT);

        this.categoryRepository.saveAndFlush(dessert);

        CategoryEntity other = new CategoryEntity();
        other.setCategoryNameEnum(CategoryNameEnum.OTHER);

        this.categoryRepository.saveAndFlush(other);
    }
}