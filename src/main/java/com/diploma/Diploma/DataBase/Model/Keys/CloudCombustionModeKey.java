package com.diploma.Diploma.DataBase.Model.Keys;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class CloudCombustionModeKey implements Serializable {
    @Column(name = "clutterClassId")
    private long clutterClassId;
    @Column(name = "explosionSensitivityId")
    private long explosionSensitivityClassId;

    public CloudCombustionModeKey(long clutterClassId, long explosionSensitivityClassId){
        this.clutterClassId = clutterClassId;
        this.explosionSensitivityClassId = explosionSensitivityClassId;
    }
}
