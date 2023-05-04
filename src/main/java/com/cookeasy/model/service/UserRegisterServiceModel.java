package com.cookeasy.model.service;

import com.cookeasy.model.entity.enumeration.GenderNameEnum;
import com.cookeasy.model.entity.enumeration.LevelNameEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterServiceModel {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private GenderNameEnum genderNameEnum;
    private LevelNameEnum levelNameEnum;
}