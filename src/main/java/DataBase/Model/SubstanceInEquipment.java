package DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Data
public class SubstanceInEquipment {
    @EmbeddedId
    private SubstanceInEquipmentKey substanceInEquipmentKey;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("equipmentId")
    @JoinColumn(name = "equipmentId")
    private Equipment equipment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("substanceId")
    @JoinColumn(name = "substanceId")
    private Substance substance;

}
