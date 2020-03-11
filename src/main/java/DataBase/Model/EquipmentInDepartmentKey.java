package DataBase.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor
public class EquipmentInDepartmentKey {
    @Column(name = "equipmentId")
    private long equipmentId;
    @Column(name = "departmentId")
    private long departmentId;

    public EquipmentInDepartmentKey(long equipmentId, long departmentId){
        this.equipmentId = equipmentId;
        this.departmentId = departmentId;
    }
}

