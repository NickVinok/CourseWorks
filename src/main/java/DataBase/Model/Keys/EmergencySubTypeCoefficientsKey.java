package DataBase.Model.Keys;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class EmergencySubTypeCoefficientsKey implements Serializable {
    @Column(name = "coefficientsId")
    private long coefficientsId;

    @Column(name = "emergencySubTYpeId")
    private long emergencySubTypeId;

    public EmergencySubTypeCoefficientsKey(long coefficientsId, long emergencySubTypeId){
        this.coefficientsId = coefficientsId;
        this.emergencySubTypeId = emergencySubTypeId;
    }
}
