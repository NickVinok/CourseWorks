package com.diploma.Diploma.DataBase.Model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "equipmentInDepartment")
public class EquipmentInDepartment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private double fullnessPercent;
    @Temporal(TemporalType.TIMESTAMP)
    private Date storageStartDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date storageEndDate;

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
    @Nullable
    private Substance substance;

    @Transient
    private String objectType = "equipmentInDepartment";
}
