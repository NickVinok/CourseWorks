package com.diploma.Diploma.DataBase.Model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "equipmentInDepartment")
public class EquipmentInDepartment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private double fullnessPercent;
    private Timestamp storageStartDate;
    private Timestamp storageEndDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("equipmentClassId")
    @JoinColumn(name = "equipmentClassId")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private EquipmentClass equipmentClass;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("departmentId")
    @JoinColumn(name = "departmentId")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private EquipmentClass department;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("substanceId")
    @JoinColumn(name = "substanceId")
    private Substance substance;
}
