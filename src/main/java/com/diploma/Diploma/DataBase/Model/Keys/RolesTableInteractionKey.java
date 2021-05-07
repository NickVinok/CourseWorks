package com.diploma.Diploma.DataBase.Model.Keys;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class RolesTableInteractionKey implements Serializable {
    @Column(name="roleId")
    private long roleId;
    @Column(name="tablesId")
    private long tableId;
    @Column(name="interactionId")
    private long interactionId;

    public RolesTableInteractionKey(long roleId, long tableId, long interactionId){
        this.interactionId=interactionId;
        this.roleId = roleId;
        this.tableId = tableId;
    }
}
