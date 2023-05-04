package com.cookeasy.service;

import com.cookeasy.model.entity.RoleEntity;
import com.cookeasy.model.entity.enumeration.RoleNameEnum;

public interface RoleService {
    void initializeRoles();
    RoleEntity findRoleByRoleName(RoleNameEnum user);
}