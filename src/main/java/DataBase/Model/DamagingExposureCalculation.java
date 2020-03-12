package DataBase.Model;

import DataBase.Model.Keys.DamagingExposureCalculationKey;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class DamagingExposureCalculation {
    @EmbeddedId
    private DamagingExposureCalculationKey damagingExposureCalculationKey;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("damagingFactorId")
    @JoinColumn(name = "damagingFactorId")
    private DamagingFactor damagingFactor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("calculationId")
    @JoinColumn(name = "calculationId")
    private Calculation calculation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("exposureTypeId")
    @JoinColumn(name = "exposureTypeId")
    private ExposureType exposureType;

    private double probitFunctionValue;
    private double exposureProbability;
    private double value;
}
