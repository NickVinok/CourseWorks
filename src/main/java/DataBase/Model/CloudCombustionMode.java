package DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CloudCombustionMode {
    @EmbeddedId
    private CloudCombustionModeKey cloudCombustionModeKey;
    @ManyToOne
    @MapsId("clutterClassId")
    @JoinColumn(name = "clutterClassId")
    private ClutterClass clutterClass;
    @ManyToOne
    @MapsId("explosionSensitivityClassId")
    @JoinColumn(name = "explosionSensitivityClassId")
    private ExplosionSensitivityClass explosionSensitivityClass;
    //TODO Имеет ли смысл выделить в отдельный класс
    private String combustionType;
    //Скорость фронта пламени
    private double flameFrontSpeed;
    //коэффициент для расчёта скорости фронта при отсутсвии такого как константы
    private double k;
    private int classificationNumber;
}
