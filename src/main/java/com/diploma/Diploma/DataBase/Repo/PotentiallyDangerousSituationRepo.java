package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.PotentiallyDangerousSituation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PotentiallyDangerousSituationRepo extends JpaRepository<PotentiallyDangerousSituation, Long> {
    List<PotentiallyDangerousSituation> findByEquipmentTypeIdAndEventIdAndDestructionTypeId
            (long equipmentTypeId, long eventId, long destructionTypeId);
    List<PotentiallyDangerousSituation> findByEquipmentTypeId(long equipmentTypeId);
}
