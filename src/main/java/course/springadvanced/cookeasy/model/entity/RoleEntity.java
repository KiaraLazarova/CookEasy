package course.springadvanced.cookeasy.model.entity;

import course.springadvanced.cookeasy.model.entity.enumeration.RoleNameEnum;
import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {
    private RoleNameEnum roleNameEnum;

    public RoleEntity() {
    }

    @Column(name = "role", nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    public RoleNameEnum getRoleNameEnum() {
        return this.roleNameEnum;
    }

    public void setRoleNameEnum(RoleNameEnum roleNameEnum) {
        this.roleNameEnum = roleNameEnum;
    }
}