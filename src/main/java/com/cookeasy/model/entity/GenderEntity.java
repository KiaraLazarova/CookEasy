package com.cookeasy.model.entity;

import com.cookeasy.model.entity.enumeration.GenderNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "genders")
public class GenderEntity extends BaseEntity {
    private GenderNameEnum genderNameEnum;

    public GenderEntity() {
    }

    @Column(name = "gender", nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    public GenderNameEnum getGenderNameEnum() {
        return this.genderNameEnum;
    }

    public void setGenderNameEnum(GenderNameEnum genderNameEnum) {
        this.genderNameEnum = genderNameEnum;
    }
}