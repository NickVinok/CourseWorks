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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("rightId")
    @JoinColumn(name = "rightId")
    private Rights rights;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("roleId")
    @JoinColumn(name = "roleId")
    private Role role;
}
