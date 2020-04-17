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
    private boolean isEnd;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("destructionTypeId")
    @JoinColumn(name = "destructionTypeId")
    private DestructionType destructionTypeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("damagingFactorId")
    @JoinColumn(name = "damagingFactorId")
    private EmergencySubType emergencySubType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("substanceType")
    @JoinColumn(name = "substanceType")
    private SubstanceType substanceType;
}
