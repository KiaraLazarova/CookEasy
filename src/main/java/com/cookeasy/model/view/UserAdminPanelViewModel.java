package com.cookeasy.model.view;

import com.cookeasy.model.entity.GenderEntity;
import com.cookeasy.model.entity.LevelEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAdminPanelViewModel {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private GenderEntity genderEntity;
    private LevelEntity levelEntity;
    private boolean admin;
}