package DataBase.Model.Keys;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class RolesRightKey implements Serializable {
    @Column(name = "rightId")
    private long rightId;
    @Column(name = "roleId")
    private long roleId;

    public RolesRightKey(long rightId, long roleId){
        this.rightId = rightId;
        this.roleId = roleId;
    }
}
