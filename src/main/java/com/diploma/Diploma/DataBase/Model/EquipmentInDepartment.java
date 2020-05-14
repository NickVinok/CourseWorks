package com.diploma.Diploma.DataBase.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "equipmentClassId", referencedColumnName = "id")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private EquipmentClass equipmentClass;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "departmentId", referencedColumnName = "id")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "substanceId", referencedColumnName = "id")
    private Substance substance;
}
