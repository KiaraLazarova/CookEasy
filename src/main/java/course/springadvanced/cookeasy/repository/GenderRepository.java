package course.springadvanced.cookeasy.repository;

import course.springadvanced.cookeasy.model.entity.GenderEntity;
import course.springadvanced.cookeasy.model.entity.enumeration.GenderNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<GenderEntity, Long> {
    GenderEntity findByGenderNameEnum(GenderNameEnum genderNameEnum);
}