package course.springadvanced.cookeasy.model.entity;

import course.springadvanced.cookeasy.model.entity.enumeration.CategoryNameEnum;
import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {
    private CategoryNameEnum categoryNameEnum;

    public CategoryEntity() {
    }

    @Column(name = "category", nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    public CategoryNameEnum getCategoryNameEnum() {
        return this.categoryNameEnum;
    }

    public void setCategoryNameEnum(CategoryNameEnum categoryNameEnum) {
        this.categoryNameEnum = categoryNameEnum;
    }
}