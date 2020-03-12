package DataBase.Repo;

import DataBase.Model.Keys.SubstanceInEquipmentKey;
import DataBase.Model.SubstanceInEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubstanceInEquipmentRepo extends JpaRepository<SubstanceInEquipment, SubstanceInEquipmentKey> {
}
