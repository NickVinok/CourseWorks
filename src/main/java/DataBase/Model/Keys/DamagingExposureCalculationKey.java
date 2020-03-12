package DataBase.Model.Keys;

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
    @Column(name = "damagingFactorId")
    private long damagingFactor;
    @Column(name = "exposureTypeId")
    private long exposureType;
    @Column(name = "radius")
    private long radius;

    public DamagingExposureCalculationKey(long calculation, long damagingFactor, long exposureType, long radius){
        this.calculation  = calculation;
        this.damagingFactor = damagingFactor;
        this.exposureType = exposureType;
        this.radius = radius;
    }
}
