package com.diploma.Diploma.DataBase.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "equipmentClass")
public class EquipmentClass {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String mark; //марка оборудования
    private double volume;
    private double height;
    private double maxTemperature;
    private double maxPressure;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipmentTypeId", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private EquipmentType equipmentType;
}
