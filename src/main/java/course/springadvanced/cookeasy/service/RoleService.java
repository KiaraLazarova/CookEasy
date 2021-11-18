package course.springadvanced.cookeasy.service;

import course.springadvanced.cookeasy.model.entity.RoleEntity;
import course.springadvanced.cookeasy.model.entity.enumeration.RoleNameEnum;

public interface RoleService {
    void initializeRoles();
    RoleEntity findRoleByRoleName(RoleNameEnum user);
}