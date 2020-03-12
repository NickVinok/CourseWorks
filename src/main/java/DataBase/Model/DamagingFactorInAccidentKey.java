package DataBase.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor
public class DamagingFactorInAccidentKey{
        @Column(name = "damagingFactorId")
        private long damagingFactorId;
        @Column(name = "accidentId")
        private long accidentId;

        public DamagingFactorInAccidentKey(long damagingFactorId, long accidentId){
            this.accidentId = accidentId;
            this.damagingFactorId = damagingFactorId;
        }


}
