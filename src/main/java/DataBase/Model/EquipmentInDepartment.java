package DataBase.Model;

import DataBase.Model.Keys.EquipmentInDepartmentKey;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
public class EquipmentInDepartment {
    @EmbeddedId
    EquipmentInDepartmentKey equipmentInDepartmentKey;

    //TODO возможно в joincolumn надо добавить функцию nullable
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("equipmentId")
    @JoinColumn(name = "equipmentId")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Equipment equipmentId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("departmentId")
    @JoinColumn(name = "departmentId")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Equipment departmentId;

    private int quantity;
}
