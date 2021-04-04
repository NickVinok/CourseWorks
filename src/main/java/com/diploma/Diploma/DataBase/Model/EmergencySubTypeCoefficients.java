package com.diploma.Diploma.DataBase.Model;

import com.diploma.Diploma.DataBase.Model.Keys.EmergencySubTypeCoefficientsKey;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "emergencySubTypeCoefficients")
public class EmergencySubTypeCoefficients {
    @EmbeddedId
    EmergencySubTypeCoefficientsKey emergencySubTypeCoefficientsKey;

    private double value;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("coefficientsId")
    @JoinColumn(name = "coefficientsId", referencedColumnName = "id")
    private Coefficients coefficients;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("emergencySubTypeId")
    @JoinColumn(name = "emergencySubTypeId", referencedColumnName = "id")
    private EmergencySubType emergencySubType;

    @Transient
    private String objectType = "emergencySubTypeCoefficients";
}
