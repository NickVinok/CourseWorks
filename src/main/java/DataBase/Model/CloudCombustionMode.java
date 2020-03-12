package DataBase.Model;

import DataBase.Model.Keys.CloudCombustionModeKey;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CloudCombustionMode {
    @EmbeddedId
    private CloudCombustionModeKey cloudCombustionModeKey;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("clutterClassId")
    @JoinColumn(name = "clutterClassId")
    private ClutterClass clutterClass;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
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
