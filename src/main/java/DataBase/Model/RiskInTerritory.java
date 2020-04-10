package DataBase.Model;

import DataBase.Model.Keys.RiskInTerritoryKey;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class RiskInTerritory {
    @EmbeddedId
    RiskInTerritoryKey riskInTerritoryKey;

    private double averageRisk;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("territoryId")
    @JoinColumn(name = "territoryId")
    private Territory territory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("calculationId")
    @JoinColumn(name = "calculationId")
    private Calculation calculation;
}
