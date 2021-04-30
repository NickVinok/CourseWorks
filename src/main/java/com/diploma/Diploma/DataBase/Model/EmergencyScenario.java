package com.diploma.Diploma.DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "emergencyScenario")
public class EmergencyScenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "isFullDestructionId", referencedColumnName = "id")
    private DestructionType destructionType;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "eventId", referencedColumnName = "id")
    private Event event;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "substanceTypeId", referencedColumnName = "id")
    private SubstanceType substanceType;

    @Transient
    private String objectType = "emergencyScenario";
}
