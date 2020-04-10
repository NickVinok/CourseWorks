package DataBase.Model;

import DataBase.Model.Keys.RolesRightKey;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class RolesRight {
    @EmbeddedId
    private RolesRightKey rolesRightKey;

    @Column(columnDefinition = "TINYINT", name="has")
    private boolean has;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("rightId")
    @JoinColumn(name = "rightId")
    private Right right;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("roleId")
    @JoinColumn(name = "roleId")
    private Role role;
}
