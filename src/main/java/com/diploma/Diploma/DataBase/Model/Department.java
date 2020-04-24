package com.diploma.Diploma.DataBase.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private double area;
    private String floorType;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clutterClassId", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private ClutterClass clutterClass;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "enterpriseId", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Enterprise enterprise;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    List<EquipmentInDepartment> equipmentInDepartments = new ArrayList<>();
}
