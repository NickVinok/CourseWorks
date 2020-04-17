package DataBase.Repo;

import DataBase.Model.PotentiallyDangerousSituation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PotentiallyDangerousSituationRepo extends JpaRepository<PotentiallyDangerousSituation, Long> {
    List<PotentiallyDangerousSituation> findByEquipmentTypeIdAndEventIdAndDestructionTypeId(long equipmentTypeId, long eventId, long destructionTypeId);
}
