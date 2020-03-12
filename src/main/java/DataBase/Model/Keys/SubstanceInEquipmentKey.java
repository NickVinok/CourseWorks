package DataBase.Model.Keys;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class SubstanceInEquipmentKey implements Serializable {
    @Column(name = "substanceId")
    private long substanceId;
    @Column(name = "equipmentId")
    private long equipmentId;

    public SubstanceInEquipmentKey (long substanceId, long equipmentId){
        this.substanceId = substanceId;
        this.equipmentId = equipmentId;
    }

}
