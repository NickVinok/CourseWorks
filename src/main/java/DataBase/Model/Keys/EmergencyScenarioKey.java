package DataBase.Model.Keys;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class EmergencyScenarioKey implements Serializable {
        @Column(name = "damagingFactorId")
        private long damagingFactor;
        @Column(name = "accidentId")
        private long accident;

        public EmergencyScenarioKey(long damagingFactor, long accident){
            this.accident = accident;
            this.damagingFactor = damagingFactor;
        }


}
