package com.cookeasy.constant;

import com.cookeasy.model.entity.enumeration.GenderNameEnum;
import com.cookeasy.model.entity.enumeration.LevelNameEnum;

import java.time.LocalDateTime;

public class GlobalTestConstants {
    public static final String FIRST_NAME = "Kalina";

    public static final String LAST_NAME = "Lazarova";

    public static final String USERNAME = "1b9e03f7a7a08fcb15cf";

    public static final String EMAIL = "1b9e03f7a7a08fcb15cf@gmail.com";

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

    public static final String COMMENT_POST_PAGE_VIEW_NAME = "comment-post";

    public static final String STATISTICS_PAGE_VIEW_NAME = "statistics-admin";

    public static final String USERS_PAGE_VIEW_NAME = "users-admin";

    public static final String COMMENTS_PAGE_VIEW_NAME = "comments-admin";

    public static final String USER_PROFILE_PAGE_VIEW_NAME = "profile";

    public static final String USER_PROFILE_EDIT_PAGE_VIEW_NAME = "profile-edit";

    public static final String UPDATED_LAST_NAME = "updated";

    public static final String INVALID_LAST_NAME = "invalid_last_name";

    public static final String RECIPES_PAGE_VIEW_NAME = "recipes";

    public static final String RECIPE_ADD_PAGE_VIEW_NAME = "recipe-add";

    public static final String ADDED_RECIPES_PAGE_VIEW_NAME = "recipes-added";

    public static final String LIKED_RECIPES_PAGE_VIEW_NAME = "recipes-liked";

    public static final String SAVED_RECIPES_PAGE_VIEW_NAME = "recipes-saved";

    public static final String COOKED_RECIPES_PAGE_VIEW_NAME = "recipes-cooked";

    public static final String ABOUT_PAGE_VIEW_NAME = "about";
}