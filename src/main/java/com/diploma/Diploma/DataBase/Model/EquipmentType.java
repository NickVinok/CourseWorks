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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "equipmentType")
    private List<PotentiallyDangerousSituation> potentiallyDangerousSituations = new ArrayList<>();

    public void addPotentiallyDangerousSituation(PotentiallyDangerousSituation potentiallyDangerousSituation){
        potentiallyDangerousSituation.setEquipmentType(this);
        potentiallyDangerousSituations.add(potentiallyDangerousSituation);
    }
    public void deleteDepartment(PotentiallyDangerousSituation potentiallyDangerousSituation){
        potentiallyDangerousSituations.remove(potentiallyDangerousSituation);
        potentiallyDangerousSituation.setEquipmentType(null);
    }
}
