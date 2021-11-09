package course.springadvanced.finalproject.service.impl;

import course.springadvanced.finalproject.model.entity.RoleEntity;
import course.springadvanced.finalproject.model.entity.enumeration.RoleNameEnum;
import course.springadvanced.finalproject.repository.RoleRepository;
import course.springadvanced.finalproject.service.RoleService;
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

        RoleEntity administrator = new RoleEntity();
        administrator.setRoleNameEnum(RoleNameEnum.ADMINISTRATOR);

        this.roleRepository.saveAndFlush(administrator);
    }
}