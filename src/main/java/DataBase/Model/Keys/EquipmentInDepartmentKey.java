package DataBase.Model.Keys;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class EquipmentInDepartmentKey implements Serializable {
    @Column(name = "equipmentId")
    private long equipmentId;
    @Column(name = "departmentId")
    private long departmentId;

    public EquipmentInDepartmentKey(long equipmentId, long departmentId){
        this.equipmentId = equipmentId;
        this.departmentId = departmentId;
    }
}

