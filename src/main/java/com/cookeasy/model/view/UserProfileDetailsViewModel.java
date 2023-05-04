package com.cookeasy.model.view;

import com.cookeasy.model.entity.GenderEntity;
import com.cookeasy.model.entity.LevelEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserProfileDetailsViewModel {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private GenderEntity genderEntity;
    private LevelEntity levelEntity;
}