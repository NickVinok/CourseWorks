package com.diploma.Diploma.DataBase.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "scenarioNode")
public class EmergencyScenarioNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private double probability;
    private boolean isEndNode;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("emergencySubTypeId")
    @JoinColumn(name = "emergencySubTypeId", referencedColumnName = "id")
    private EmergencySubType emergencySubType;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("emergencyScenarioId")
    @JoinColumn(name = "emergencyScenarioId", referencedColumnName = "id")
    private EmergencyScenario emergencyScenario;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("scenarioNodeId")
    @JoinColumn(name = "scenarioNodeId", referencedColumnName = "id")
    private EmergencyScenarioNode emergencyScenarioNode;

    @Transient
    private String objectType = "emergencyScenarioNode";
}
