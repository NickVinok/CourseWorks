package DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class DamagingFactorInAccident {
    @EmbeddedId
    private DamagingFactorInAccidentKey damagingFactorInAccidentKey;

    private double probability;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("accidentId")
    @JoinColumn(name = "accidentId")
    private Accident accidentId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("damagingFactorId")
    @JoinColumn(name = "damagingFactorId")
    private DamagingFactor damagingFactor;
}
