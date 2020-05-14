package com.diploma.Diploma.DataBase.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "potentiallyDangerousSituation")
public class PotentiallyDangerousSituation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private double holeDiameter;
    private double depressurizationFrequency;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "equipmentTypeId", referencedColumnName = "id")
    private EquipmentType equipmentType;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "eventId", referencedColumnName = "id")
    private Event event;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "destructionTypeId", referencedColumnName = "id")
    private DestructionType destructionType;
}
