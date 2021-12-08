package course.springadvanced.cookeasy.constant;

import course.springadvanced.cookeasy.model.entity.enumeration.GenderNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;

public class GlobalTestConstants {
    public static final String FIRST_NAME = "Kalina";

    public static final String LAST_NAME = "Lazarova";

    public static final String USERNAME = "kalina_lazarova";

    public static final String EMAIL = "kalina_lazarova@gmail.com";

    public static final String PASSWORD = "12345";

    public static final String INVALID_USERNAME = "invalid_username";

    public static final String EXPECTED_ROLES = "ROLE_ADMIN, ROLE_USER";

    public static final String REGISTER_PAGE_VIEW_NAME = "auth-register";

    public static final String GENDER_NAME_ENUM = GenderNameEnum.FEMALE.name();

    public static final String LEVEL_NAME_ENUM = LevelNameEnum.ADVANCED.name();
}