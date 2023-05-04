package com.cookeasy.model.service;

import com.cookeasy.model.entity.enumeration.GenderNameEnum;
import com.cookeasy.model.entity.enumeration.LevelNameEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserProfileEditServiceModel {
    private String firstName;
    private String lastName;
    private GenderNameEnum genderNameEnum;
    private LevelNameEnum levelNameEnum;
}