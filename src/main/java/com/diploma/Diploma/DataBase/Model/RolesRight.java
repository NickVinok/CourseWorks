package com.diploma.Diploma.DataBase.Model;

import com.diploma.Diploma.DataBase.Model.Keys.RolesRightKey;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rolesRight")
public class RolesRight {
    @EmbeddedId
    private RolesRightKey rolesRightKey;

    @Column(columnDefinition = "TINYINT", name="has")
    private boolean has;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("rightId")
    @JoinColumn(name = "rightId", referencedColumnName = "id")
    private Rights rights;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("roleId")
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    private Role role;

    @Transient
    private String objectType = "rolesRight";
}
