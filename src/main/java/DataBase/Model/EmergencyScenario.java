package DataBase.Model;

import DataBase.Model.Keys.EmergencyScenarioKey;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class EmergencyScenario {
    @EmbeddedId
    private EmergencyScenarioKey emergencyScenarioKey;

    private double probability;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("potentiallyDangerousSituationId")
    @JoinColumn(name = "potentiallyDangerousSituationId")
    private PotentiallyDangerousSituation potentiallyDangerousSituationId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("damagingFactorId")
    @JoinColumn(name = "damagingFactorId")
    private EmergencySubType emergencySubType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("substanceType")
    @JoinColumn(name = "substanceType")
    private SubstanceType substanceType;
}
