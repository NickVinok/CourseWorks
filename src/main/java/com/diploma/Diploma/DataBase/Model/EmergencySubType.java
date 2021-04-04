package com.diploma.Diploma.DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "emergencySubType")
public class EmergencySubType {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "emergencyId", referencedColumnName = "id")
    private Emergency emergency;

    @Transient
    private String objectType = "emergencySubType";
}
