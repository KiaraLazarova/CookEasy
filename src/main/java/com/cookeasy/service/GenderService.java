package com.cookeasy.service;

import com.cookeasy.model.entity.GenderEntity;
import com.cookeasy.model.entity.enumeration.GenderNameEnum;

public interface GenderService {
    void initializeGenders();
    GenderEntity findGenderByGenderName(GenderNameEnum genderNameEnum);
}