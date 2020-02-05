package DataBase.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor
public class CloudCombustionModeKey {
    @Column(name = "clutterClassId")
    private long clutterClassId;
    @Column(name = "explosionSensitivityClassId")
    private long explosionSensitivityClassId;

    public CloudCombustionModeKey(long clutterClassId, long explosionSensitivityClassId){
        this.clutterClassId = clutterClassId;
        this.explosionSensitivityClassId = explosionSensitivityClassId;
    }
}
