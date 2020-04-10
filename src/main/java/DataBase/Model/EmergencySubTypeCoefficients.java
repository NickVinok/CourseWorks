package DataBase.Model;

import DataBase.Model.Keys.EmergencySubTypeCoefficientsKey;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class EmergencySubTypeCoefficients {
    @EmbeddedId
    EmergencySubTypeCoefficientsKey emergencySubTypeCoefficientsKey;

    private double value;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("coefficientsId")
    @JoinColumn(name = "coefficientsId")
    private Coefficients coefficients;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("emergencySubTypeId")
    @JoinColumn(name = "emergencySubTypeId")
    private EmergencySubType emergencySubType;
}
