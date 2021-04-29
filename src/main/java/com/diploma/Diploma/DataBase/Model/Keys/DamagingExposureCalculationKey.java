package com.diploma.Diploma.DataBase.Model.Keys;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class DamagingExposureCalculationKey implements Serializable {
    @Column(name = "calculationId")
    private long calculation;
    @Column(name = "scenarioNodeId")
    private long scenarioNodeId;
    @Column(name = "exposureTypeId")
    private long exposureType;
    @Column(name = "radius")
    private double radius;

    public DamagingExposureCalculationKey(long calculation, long scenarioNodeId, long exposureType, double radius){
        this.calculation  = calculation;
        this.scenarioNodeId = scenarioNodeId;
        this.exposureType = exposureType;
        this.radius = radius;
    }
}
