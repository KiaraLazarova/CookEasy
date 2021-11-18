package course.springadvanced.cookeasy.service;

import course.springadvanced.cookeasy.model.entity.GenderEntity;
import course.springadvanced.cookeasy.model.entity.enumeration.GenderNameEnum;

public interface GenderService {
    void initializeGenders();
    GenderEntity findGenderByGenderName(GenderNameEnum genderNameEnum);
}