package course.springadvanced.cookeasy.constant;

import course.springadvanced.cookeasy.model.entity.enumeration.GenderNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;

import java.time.LocalDateTime;

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

    public static final String INDEX_PAGE_VIEW_NAME = "index";

    public static final String QUOTES_PAGE_VIEW_NAME = "quotes";

    public static final String LOGIN_PAGE_VIEW_NAME = "auth-login";

    public static final String RECIPE_TITLE = "Test recipe title";

    public static final String RECIPE_DESCRIPTION = "Test recipe description here...";

    public static final LocalDateTime RECIPE_CREATED_ON = LocalDateTime.parse("2021-12-05T13:45:02.345605");

    public static final int RECIPE_PREPARATION_TIME = 300;

    public static final LocalDateTime COMMENT_CREATED_ON = LocalDateTime.parse("2021-12-09T13:45:02.345605");

    public static final String COMMENT_CONTENT = "Test comment :)";

    public static final boolean COMMENT_ARCHIVED = false;

    public static final boolean COMMENT_APPROVED = true;
}