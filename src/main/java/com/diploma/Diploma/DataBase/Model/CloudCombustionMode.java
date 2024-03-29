package com.diploma.Diploma.DataBase.Model;

import com.diploma.Diploma.DataBase.Model.Keys.CloudCombustionModeKey;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cloudCombustionMode")
public class CloudCombustionMode {
    @EmbeddedId
    private CloudCombustionModeKey cloudCombustionModeKey;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("clutterClassId")
    @JoinColumn(name = "clutterClassId")
    private ClutterClass clutterClass;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("explosionSensitivityId")
    @JoinColumn(name = "explosionSensitivityId")
    private ExplosionSensitivity explosionSensitivity;
    private String combustionType;
    //Скорость фронта пламени
    private double flameFrontSpeed;
    //коэффициент для расчёта скорости фронта при отсутсвии такого как константы
    private double k;
    private int classificationNumber;

    @Transient
    private String objectType = "cloudCombustionMode";
}
