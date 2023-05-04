package com.cookeasy.repository;

import com.cookeasy.model.entity.RoleEntity;
import com.cookeasy.model.entity.enumeration.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByRoleNameEnum(RoleNameEnum roleNameEnum);
}