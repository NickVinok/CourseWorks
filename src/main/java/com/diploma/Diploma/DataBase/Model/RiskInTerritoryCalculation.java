package com.diploma.Diploma.DataBase.Model;

import com.diploma.Diploma.DataBase.Model.Keys.RiskInTerritoryCalculationKey;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "riskInTerritoryCalculation")
public class RiskInTerritoryCalculation {
    @EmbeddedId
    RiskInTerritoryCalculationKey riskInTerritoryCalculationKey;

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
