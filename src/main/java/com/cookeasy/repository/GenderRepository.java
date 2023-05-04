package com.cookeasy.repository;

import com.cookeasy.model.entity.GenderEntity;
import com.cookeasy.model.entity.enumeration.GenderNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<GenderEntity, Long> {
    GenderEntity findByGenderNameEnum(GenderNameEnum genderNameEnum);
}