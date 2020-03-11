package DataBase.Model;

import lombok.Data;

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
    private Equipment equipmentId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("departmentId")
    @JoinColumn(name = "departmentId")
    private Equipment departmentId;

    private int quantity;
}
