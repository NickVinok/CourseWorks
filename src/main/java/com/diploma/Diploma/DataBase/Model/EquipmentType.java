package com.diploma.Diploma.DataBase.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "equipmentType")
public class EquipmentType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String name;

    @Transient
    private String objectType = "equipmentType";
}
