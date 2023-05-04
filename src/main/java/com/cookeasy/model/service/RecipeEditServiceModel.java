package com.cookeasy.model.service;

import com.cookeasy.model.entity.enumeration.CategoryNameEnum;
import com.cookeasy.model.entity.enumeration.LevelNameEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecipeEditServiceModel {
    private int hours;
    private int minutes;
    private CategoryNameEnum categoryNameEnum;
    private LevelNameEnum levelNameEnum;
    private String description;
}