package DataBase.Model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
public class Equipment {
    @Id
    private long id;
    private String name; //наименованование оборудования
    private String equipmentMark; //марка оборудования
    private double volume;
    private double height;
    private double minTemperature;
    private double maxTemperature;
    private double minPressure;  //неуверен есть ли смысл, ибо минимальное давение по сути - атмосферное
    private double maxPressure;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "substanceTypeId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SubstanceType substanceType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipmentTypeId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EquipmentType equipmentType;
}
