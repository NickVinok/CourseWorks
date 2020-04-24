package com.diploma.Diploma.DataBase.Model.Keys;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class RiskInTerritoryCalculationKey implements Serializable {
    @Column(name = "territoryId")
    private long territoryId;
    @Column(name = "calculationId")
    private long calculationId;

    public RiskInTerritoryCalculationKey(long territoryId, long calculationId){
        this.territoryId = territoryId;
        this.calculationId = calculationId;
    }
}
