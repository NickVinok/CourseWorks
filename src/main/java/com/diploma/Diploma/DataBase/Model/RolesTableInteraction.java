package com.diploma.Diploma.DataBase.Model;

import com.diploma.Diploma.DataBase.Model.Keys.RolesTableInteractionKey;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rolesTableInteraction")
public class RolesTableInteraction {
    @EmbeddedId
    private RolesTableInteractionKey rolesTableInteractionKey;

    private boolean has;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("interactionId")
    @JoinColumn(name = "interactionId", referencedColumnName = "id")
    private Interaction interaction;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("roleId")
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("tablesId")
    @JoinColumn(name = "tablesId", referencedColumnName = "id")
    private Tables tables;

    @Transient
    private String objectType = "rolesTableInteraction";
}
