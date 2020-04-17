package DataBase.Model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class EquipmentInDepartment {
    @Id
    private long id;

    private double fullnessPercent;
    private Timestamp storageStartDate;
    private Timestamp storageEndDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("equipmentId")
    @JoinColumn(name = "equipmentId")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private EquipmentClass equipmentClass;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("departmentId")
    @JoinColumn(name = "departmentId")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private EquipmentClass department;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("substanceId")
    @JoinColumn(name = "substanceId")
    private Substance substance;
}
