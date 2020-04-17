package DataBase.Model;

import DataBase.Model.Keys.DamagingExposureCalculationKey;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class EmergencySubTypeDamageCalculation {
    @EmbeddedId
    private DamagingExposureCalculationKey damagingExposureCalculationKey;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("emergencySubTypeId")
    @JoinColumn(name = "emergencySubTypeId")
    private EmergencySubType emergencySubType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("calculationId")
    @JoinColumn(name = "calculationId")
    private Calculation calculation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("exposureTypeId")
    @JoinColumn(name = "exposureTypeId")
    private ExposureType exposureType;

    private double probitFunctionValue;
    private double probability;
    private double value;
    private double potentialRisk;
}
