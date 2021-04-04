package com.diploma.Diploma.DataBase.Model;

import com.diploma.Diploma.DataBase.Model.Keys.DamagingExposureCalculationKey;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "emergencySubTypeDamageCalculation")
public class EmergencySubTypeDamageCalculation {
    @EmbeddedId
    private DamagingExposureCalculationKey damagingExposureCalculationKey;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("emergencySubTypeId")
    @JoinColumn(name = "emergencySubTypeId", referencedColumnName = "id")
    private EmergencySubType emergencySubType;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("calculationId")
    @JoinColumn(name = "calculationId", referencedColumnName = "id")
    private Calculation calculation;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("exposureTypeId")
    @JoinColumn(name = "exposureTypeId", referencedColumnName = "id")
    private ExposureType exposureType;

    private double probitFunctionValue;
    private double probability;
    private double value;
    private double potentialRisk;

    @Transient
    private String objectType = "emergencySubTypeDamageCalculation";
}
