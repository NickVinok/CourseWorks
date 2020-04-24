package com.diploma.Diploma.DataBase.Model;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("equipmentTypeId")
    @JoinColumn(name = "equipmentTypeId")
    private EquipmentType equipmentType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("eventId")
    @JoinColumn(name = "eventId")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("destructionTypeId")
    @JoinColumn(name = "destructionTypeId")
    private DestructionType destructionType;
}
