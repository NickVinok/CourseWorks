package com.diploma.Diploma.DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "substance")
public class Substance {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String name;
    private String type;
    private double specificHeat; //удельная теплоёмкость
    private double beta; //корректировачный параметр для расчёта режима сгорания облака
    private double boilingTemperature; //Температура кипения при нормальном атмосферном давлении
    private double specificBurnoutRate; //Удельная теплота сгорания кДж/кг
    private double specificEvaporationRate; //Удельная теплота испарения жидкости кДж/кг
    private double fuelVapourDensity; //Плотность насыщенных паров топлива при температуре кипения кг\м3
    private double density; //Плотность жидкости кг/м3
    private double specificEvaporationHeat; //Удельная теплота испарения сжиженного газа при температуре кипения жидкости
    private double molarMass; //Молярная масса
    private double concentrationLimitMinimalValue; //Нижний концентрационный предел

    //TODO Нормально вставить декоратор от Hibernate и разобраться с OnDelete (Сделать двусторонюю связь)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "explosionSensitivityId", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private ExplosionSensitivity explosionSensitivity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "substanceTypeId", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private SubstanceType substanceType;
}
