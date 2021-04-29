package com.diploma.Diploma.DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "isFullDestruction")
public class DestructionType {
    @Id
    @Column(columnDefinition="tinyint(1)")
    private Boolean id;

    @Transient
    private String objectType = "destructionType";
}
