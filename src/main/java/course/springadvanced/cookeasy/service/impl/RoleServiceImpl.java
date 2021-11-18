package course.springadvanced.cookeasy.service.impl;

import course.springadvanced.cookeasy.model.entity.RoleEntity;
import course.springadvanced.cookeasy.model.entity.enumeration.RoleNameEnum;
import course.springadvanced.cookeasy.repository.RoleRepository;
import course.springadvanced.cookeasy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initializeRoles() {
        if(this.roleRepository.count() != 0) return;

        RoleEntity user = new RoleEntity();
        user.setRoleNameEnum(RoleNameEnum.USER);

        this.roleRepository.saveAndFlush(user);

        RoleEntity admin = new RoleEntity();
        admin.setRoleNameEnum(RoleNameEnum.ADMIN);

        this.roleRepository.saveAndFlush(admin);
    }

    @Override
    public RoleEntity findRoleByRoleName(RoleNameEnum roleNameEnum) {
        return this.roleRepository.findByRoleNameEnum(roleNameEnum);
    }
}